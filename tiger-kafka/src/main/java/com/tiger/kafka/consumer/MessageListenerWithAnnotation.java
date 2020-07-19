package com.tiger.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AbstractConsumerSeekAware;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Zeng Hu
 * @Date: 2020/7/18 21:34
 * @Description:
 * @Version: 1.0
 **/
@Component
@Profile("consumer")
public class MessageListenerWithAnnotation {
    private static final Logger LOG = LoggerFactory.getLogger(MessageListenerWithAnnotation.class);

    @KafkaListener(topics = "${consumer.topics}", containerFactory = "listenerContainerFactory")
    public void bacthMessage(List<ConsumerRecord<String, String>> records) {
        for (ConsumerRecord record : records) {
            LOG.info("receive - key:{}, value:{}", record.key(), record.value());
        }
    }

}
