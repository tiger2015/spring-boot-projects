package com.tiger.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.List;

/**
 * @Auther: Zeng Hu
 * @Date: 2020/7/18 21:34
 * @Description:
 * @Version: 1.0
 **/
//@Component
@Profile("consumer")
@Slf4j
public class MessageListenerWithAnnotation {

    @KafkaListener(topics = "${consumer.topics}", containerFactory = "listenerContainerFactory")
    public void bacthMessage(List<ConsumerRecord<String, String>> records) {
        for (ConsumerRecord record : records) {
            log.info("receive - key:{}, value:{}", record.key(), record.value());
        }
    }

}
