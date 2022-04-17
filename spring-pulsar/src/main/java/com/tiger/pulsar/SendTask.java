package com.tiger.pulsar;

import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.MessageId;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

/**
 * @Author Zenghu
 * @Date 2022/3/20
 * @Description
 * @Version: 1.0
 **/
@Component
@Slf4j
public class SendTask {

    private final Producer<byte[]> producer;

    public SendTask(Producer<byte[]> producer) {
        this.producer = producer;
    }

    @Scheduled(initialDelay = 0, fixedRate = 1000)
    public void send() {

        try {
            long time = System.currentTimeMillis() / 1000L;
            MessageId send = producer.newMessage()
                    .key("hello")
                    .value((time + "").getBytes(Charset.defaultCharset())).send();
            // MessageId send = producer.send("hello".getBytes(Charset.defaultCharset()));
            log.info("message id:{},value:{}", send, time);
        } catch (PulsarClientException e) {
            e.printStackTrace();
        }


    }


}
