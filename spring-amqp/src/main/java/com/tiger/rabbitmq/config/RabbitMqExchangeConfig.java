package com.tiger.rabbitmq.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqExchangeConfig {

    @Bean(name = "directExchange")
    DirectExchange defaultDirectExchange(@Value("${rabbit.exchange.directExchange}") String name){
        return new DirectExchange(name);
    }

    @Bean(name = "topicExchange")
    TopicExchange defaultTopicExchange(@Value("${rabbit.exchange.topicExchange}") String name){
        return new TopicExchange(name);
    }

}
