package com.tiger.rabbitmq.task;

import com.tiger.rabbitmq.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@EnableScheduling
public class Sender {
    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private MessageService messageService;

    @Value("${rabbit.exchange.directExchange}")
    private String exchange;

    @Value("${rabbit.routingKey}")
    private String routingKey;

    private AtomicInteger index = new AtomicInteger(0);

    //@Scheduled(fixedRate = 10)
    public void sendMessage() {
        messageService.sendMessage(exchange, routingKey, "test-"+index.incrementAndGet());
        LOGGER.info("send message");
    }

}
