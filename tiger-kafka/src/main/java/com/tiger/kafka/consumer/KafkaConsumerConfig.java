package com.tiger.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.ContainerCustomizer;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;

/**
 * @Auther: Zeng Hu
 * @Date: 2020/7/18 21:18
 * @Description:
 * @Version: 1.0
 **/
@Configuration
@EnableKafka
@Profile("consumer")
public class KafkaConsumerConfig {
    private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumerConfig.class);

    @Value("${consumer.concurrency}")
    private int concurency;

    @Value("${consumer.topics}")
    private String[] topics;

    @Autowired
    private KafkaProperties properties;

    @Bean
    public ConcurrentMessageListenerContainer listenerContainer() {
        ContainerProperties containerProperties = new ContainerProperties(topics);
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
        LOG.info("init current kafka listener container factory");
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
