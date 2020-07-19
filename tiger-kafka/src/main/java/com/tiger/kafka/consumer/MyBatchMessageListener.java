package com.tiger.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.AbstractConsumerSeekAware;
import org.springframework.kafka.listener.BatchMessageListener;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: Zeng Hu
 * @Date: 2020/7/19 17:50
 * @Description:
 * @Version: 1.0
 **/
public class MyBatchMessageListener<K, V> extends AbstractConsumerSeekAware implements BatchMessageListener<K, V> {
    private static final Logger LOG = LoggerFactory.getLogger(MyBatchMessageListener.class);
    private final ThreadLocal<ConsumerSeekCallback> callbackThreadLocal = new ThreadLocal<>();
    private final Map<TopicPartition, ConsumerSeekCallback> callbacks = new ConcurrentHashMap<>();

    @Override
    public void onMessage(List<ConsumerRecord<K, V>> data) {
        for (ConsumerRecord record : data) {
            LOG.info("receive - key:{}, value:{}", record.key(), record.value());
        }
    }

    @Override
    public void registerSeekCallback(ConsumerSeekCallback callback) {
        this.callbackThreadLocal.set(callback);
    }

    @Override
    public void onPartitionsAssigned(Map<TopicPartition, Long> assignments, ConsumerSeekCallback callback) {
         assignments.keySet().forEach(tp->this.callbacks.put(tp, this.callbackThreadLocal.get()));
         LOG.info("partition assigned");
         callback.seekToEnd(assignments.keySet());
    }

    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
        partitions.forEach(tp-> this.callbacks.remove(tp));
        this.callbackThreadLocal.remove();
    }
}
