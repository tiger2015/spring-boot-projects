package com.tiger.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Auther: Zeng Hu
 * @Date: 2020/7/18 22:38
 * @Description:
 * @Version: 1.0
 **/
@Component
@Profile("producer")
public class MessageProducer {
    private static final Logger LOG = LoggerFactory.getLogger(MessageProducer.class);

    @Autowired
    @Qualifier("kafkaTemplate")
    private KafkaTemplate template;

    @Value("${kafka.producer.topic}")
    private String topic;


    @Scheduled(fixedRate = 100)
    public void sendMessage() {
        long current = System.currentTimeMillis();
        template.send(topic, "msg1", "msg1-" + current);
        LOG.info("send msg1");
    }

    @Scheduled(fixedRate = 100)
    public void sendMessage2() {
        long current = System.currentTimeMillis();
        template.send(topic, "msg2", "msg2-" + current);
        LOG.info("send msg2");
    }
}
