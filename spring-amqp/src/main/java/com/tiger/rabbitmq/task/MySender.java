package com.tiger.rabbitmq.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
@Slf4j
public class MySender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Scheduled(fixedRate = 1000)
    public void send1() {
        // 向队列queue1中发送消息
        amqpTemplate.convertAndSend("queue1", "hello");
    }

    @Scheduled(fixedRate = 1000)
    public void send2(){
        // 向exchange中发送消息
        amqpTemplate.convertAndSend("exchange","","hello1");
    }

    @Scheduled(fixedRate = 1000)
    public void send3(){
        amqpTemplate.convertAndSend("exchange","hello","hello2");
    }
}
