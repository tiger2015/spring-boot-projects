package com.tiger.kafka.producer;

import com.tiger.kafka.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

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

    @Value("${spring.kafka.producer.topic}")
    private String topic;

    private AtomicLong index = new AtomicLong(0);


    @Scheduled(fixedRate = 1000)
    public void sendMessage() {
        long current = System.currentTimeMillis();

        /**
         for (int i = 0; i < 100; i++) {
         template.send(topic, "msg-"+i, "msg1-"+current);
         }
         **/
        User user = new User();
        user.setId(index.getAndIncrement());
        user.setName("admin-" + index.get());
        user.setBirthday(LocalDate.now());
        user.setFavorites(new ArrayList<>(Arrays.asList("basketball", "hiking")));
        user.setSalary(3500);

        template.send(topic, user.getId(), user).addCallback(new ListenableFutureCallback() {
            @Override
            public void onFailure(Throwable ex) {
                LOG.error("send msg fail", ex);
            }

            @Override
            public void onSuccess(Object result) {
                LOG.info("send msg success");
            }
        });

    }
}
