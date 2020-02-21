package com.tiger.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqBindingConfig {

    //@Bean
    public Binding defaultBinding(@Autowired @Qualifier("queue") Queue queue, @Autowired DirectExchange exchange, @Value("${rabbit.routingKey}") String routingKey) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

}
