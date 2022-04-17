package com.tiger.pulsar;


import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.MessageListener;
import org.apache.pulsar.client.api.PulsarClientException;

import java.nio.charset.Charset;

/**
 * @Author Zenghu
 * @Date 2022/3/21
 * @Description
 * @Version: 1.0
 **/
@Slf4j
public class MyMessageListener<T> implements MessageListener<T> {
   public void received(Consumer<T> consumer, Message<T> msg) {
        try {

            log.info("consumer:{} receive:{}", consumer.getConsumerName(), new String((byte[]) msg.getValue(), Charset.defaultCharset()));
            consumer.acknowledge(msg);
        } catch (PulsarClientException e) {
            e.printStackTrace();
            // 会重传
            consumer.negativeAcknowledge(msg);
        }
    }
}
