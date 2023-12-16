package com.tiger.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;

/**
 * @Auther: Zeng Hu
 * @Date: 2020/7/18 22:26
 * @Description:
 * @Version: 1.0
 **/
@Slf4j
public class MessageListener implements ProducerListener {

    @Override
    public void onError(ProducerRecord producerRecord, Exception exception) {
        log.info("send message error");

    }

    @Override
    public void onSuccess(ProducerRecord producerRecord, RecordMetadata recordMetadata) {
        log.info("send message success, partition-{}", recordMetadata.partition());
    }
}
