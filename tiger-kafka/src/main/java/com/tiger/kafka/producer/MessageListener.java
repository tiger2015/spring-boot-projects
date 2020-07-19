package com.tiger.kafka.producer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.ProducerListener;

/**
 * @Auther: Zeng Hu
 * @Date: 2020/7/18 22:26
 * @Description:
 * @Version: 1.0
 **/
public class MessageListener implements ProducerListener {
    private static final Logger LOG = LoggerFactory.getLogger(MessageListener.class);

    @Override
    public void onError(ProducerRecord producerRecord, Exception exception) {

    }

    @Override
    public void onSuccess(ProducerRecord producerRecord, RecordMetadata recordMetadata) {
    }
}
