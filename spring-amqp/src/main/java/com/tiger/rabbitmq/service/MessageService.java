package com.tiger.rabbitmq.service;


public interface MessageService {
    void sendMessage(String exchange, String routingKey, Object message);
}
