package com.tiger.consumer.redis.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.tiger.consumer.redis.listener.RedisMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.BeanUtilsHashMapper;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.util.ErrorHandler;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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




    //@Bean
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
