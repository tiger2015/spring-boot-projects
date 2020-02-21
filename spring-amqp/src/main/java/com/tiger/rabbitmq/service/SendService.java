package com.tiger.rabbitmq.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class SendService {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Scheduled(fixedRate = 10)
    public void sendMessage(){
        amqpTemplate.convertAndSend("hello");
    }
}
