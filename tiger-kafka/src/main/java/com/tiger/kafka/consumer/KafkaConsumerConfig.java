package com.tiger.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.TopicPartitionOffset;

/**
 * @Auther: Zeng Hu
 * @Date: 2020/7/18 21:18
 * @Description:
 * @Version: 1.0
 **/
@Configuration
@EnableKafka
@Profile("consumer")
@Slf4j
public class KafkaConsumerConfig {

    @Value("${consumer.concurrency}")
    private int concurency;

    @Value("${consumer.topics}")
    private String[] topics;

    @Autowired
    private KafkaProperties properties;

    @Bean
    public ConcurrentMessageListenerContainer listenerContainer() {

        // 消费指定的分区
        TopicPartitionOffset p0 = new TopicPartitionOffset(topics[0], 1);
        TopicPartitionOffset p1 = new TopicPartitionOffset(topics[0], 2);

         ContainerProperties containerProperties = new ContainerProperties(topics);
        //ContainerProperties containerProperties = new ContainerProperties(p0, p1);
        containerProperties.setMessageListener(new MyBatchMessageListener());
        containerProperties.setAckMode(ContainerProperties.AckMode.BATCH);
        // containerProperties.setConsumerRebalanceListener();


        ConcurrentMessageListenerContainer container = new ConcurrentMessageListenerContainer(consumerFactory(),
                containerProperties);


        container.setConcurrency(concurency);
        return container;
    }


    // 通过注解方式配置
    @Bean(name = "listenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory containerFactory() {
        log.info("init current kafka listener container factory");
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(concurency);
        factory.setBatchListener(true);
        factory.afterPropertiesSet();
        return factory;
    }

    public ConsumerFactory consumerFactory() {

        ConsumerFactory factory = new DefaultKafkaConsumerFactory(properties.buildConsumerProperties());



        return factory;
    }
}
