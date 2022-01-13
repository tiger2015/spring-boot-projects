package com.tiger.kafka.producer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

/**
 * @Auther: Zeng Hu
 * @Date: 2020/7/18 21:50
 * @Description:
 * @Version: 1.0
 **/
@Configuration
@Profile("producer")
public class KafkaProducerConfig {
    @Autowired
    private KafkaProperties properties;

    private ProducerFactory producerFactory() {
        ProducerFactory factory = new DefaultKafkaProducerFactory(properties.buildProducerProperties());
        return factory;
    }

    @Bean("kafkaTemplate")
    public KafkaTemplate kafkaTemplate() {
        KafkaTemplate template = new KafkaTemplate(producerFactory());
        template.setProducerListener(new MessageListener());
        return template;
    }

}
