package com.tiger.consumer.redis.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.sun.xml.internal.ws.message.stream.StreamMessage;
import com.tiger.consumer.redis.listener.RedisMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.BeanUtilsHashMapper;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;
import org.springframework.util.ErrorHandler;

import java.time.Duration;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

@Configuration
@Slf4j
public class RedisConfig {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;


    @Bean(name = "template")
    RedisTemplate<String, Object> defaultTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new FastJsonRedisSerializer<>(Object.class));
        // template.setValueSerializer(new JdkSerializationRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        ///template.setHashValueSerializer(new JdkSerializationRedisSerializer());
        template.setHashValueSerializer(new FastJsonRedisSerializer<>(Object.class));
        template.afterPropertiesSet();
        return template;
    }




    @Bean
    public StreamMessageListenerContainer listenerContainer() {

        StreamMessageListenerContainer.StreamMessageListenerContainerOptions<String, ObjectRecord<String, String>> listenerContainerOptions = StreamMessageListenerContainer.StreamMessageListenerContainerOptions.builder()
                .objectMapper(new BeanUtilsHashMapper<>(String.class))
                .executor(new ThreadPoolExecutor(4,4, 120, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1024)))
                .errorHandler(new ErrorHandler() {
                    @Override
                    public void handleError(Throwable t) {
                        log.error("handle message error", t);
                    }
                })
                .build();
        // '>'  '$'
        StreamOffset<String> offset = StreamOffset.create("test", ReadOffset.lastConsumed());
        StreamMessageListenerContainer<String, ObjectRecord<String, String>> streamMessageListenerContainer = StreamMessageListenerContainer.create(redisConnectionFactory, listenerContainerOptions);


        for(int i=0; i< 4;i++){
            StreamMessageListenerContainer.StreamReadRequest<String> streamReadRequest = StreamMessageListenerContainer.StreamReadRequest
                    .builder(offset)
                    .consumer(Consumer.from("test","test-"+i))
                    .cancelOnError(throwable -> false) // 出现异常时重新订阅
                    .autoAck(true)
                    .build();
            streamMessageListenerContainer.register(streamReadRequest, new RedisMessageListener());
        }
       //Subscription subscription = streamMessageListenerContainer.receiveAutoAck(Consumer.from("test", "c1"), offset, new RedisMessageListener());
        // streamMessageListenerContainer.receive(Consumer.from("test","test"), offset, new RedisMessageListener());


        streamMessageListenerContainer.start();
        return streamMessageListenerContainer;
    }

}
