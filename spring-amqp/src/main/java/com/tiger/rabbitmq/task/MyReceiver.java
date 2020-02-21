package com.tiger.rabbitmq.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyReceiver {

    // 需要手动创建队列queue
    @RabbitListener(queues = {"queue"})
    public void processHello(String message) {
        log.info("receive queue:" + message);
    }

    // 自动创建队列
    @RabbitListener(queuesToDeclare = @Queue(name = "queue", durable = "true"))
    public void process1(String message) {
        log.info("process1 receive:" + message);
    }

    // 自动创建队列
    @RabbitListener(bindings = @QueueBinding(value = @Queue("queue1"), exchange = @Exchange("exchange")))
    public void process2(String message) {
        log.info("process2 receive:" + message);
    }

    // 自动创建队列queue3, routing key=hello
    @RabbitListener(bindings = @QueueBinding(value = @Queue("queue1"), exchange = @Exchange("exchange"), key = "hello"))
    public void process3(String message) {
        log.info("process3 receive:" + message);
    }

    // exchange绑定到queue1和queue2
    @RabbitListener(bindings = {@QueueBinding(value = @Queue("queue1"), exchange = @Exchange("exchange")), @QueueBinding(value = @Queue("queue2"), exchange = @Exchange("exchange"))})
    public void process4(String message) {
        log.info("process4 receive:" + message);
    }

}
