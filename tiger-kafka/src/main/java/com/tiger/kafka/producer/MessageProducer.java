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


    @Scheduled(fixedDelay = 1000)
    public void sendMessage() {
        long current = System.currentTimeMillis();
        String hex="D3008A43249D2A6C69A00020A405218400000000202000007FFFA2A5A622A326A226948720AD110AA4D65E89F987F0FC28E85026A9ED46059A4AED9D7539E657C4ACF116223218C77199FE023BF78D80269200986CFA4D43E8B82056890155A887387A1CB9D857B0A15E55849A0012A8BF7F875DECA4FFFFFFFFFFFFFFFF8000698E16DD4E59E77DD565A5D8009293F3";

         for (int i = 0; i < 1; i++) {
         template.send(topic, null, hex);
         }

       //  LOG.info("finish send message");

        /**
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

         **/
//        try {
//            template.send(topic,user.getId(),user).get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }


    }
}
