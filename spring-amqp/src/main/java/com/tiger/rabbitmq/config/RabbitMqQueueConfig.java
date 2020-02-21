package com.tiger.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqQueueConfig {

    @Bean(name = "queue")
    Queue defaultQueue(@Value("${rabbit.queue}") String name) {
        // 持久化
        return new Queue(name, true);
    }

}
