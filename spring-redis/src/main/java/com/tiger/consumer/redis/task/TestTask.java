package com.tiger.consumer.redis.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author Zenghu
 * @Date 2022/1/8
 * @Description
 * @Version: 1.0
 **/
@Component
@Slf4j
public class TestTask {
    private volatile int count = 0;
    private ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(4);

    @Autowired
    @Qualifier("template")
    private RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    public void init(){
        // 遇到异常，定时任务会结束
       // executorService.scheduleAtFixedRate(new Task(), 0, 1, TimeUnit.SECONDS);
       // log.info("start 自定义任务");
    }


    @Scheduled(initialDelay = 0, fixedRate = 1000)
    public void send() {
       for(int i=0; i<2000;i++){
           ObjectRecord<String, String> record = StreamRecords.newRecord().in("test")
                   .withId("*")
                   .ofObject("test-" + i);
           redisTemplate.opsForStream().add(record);

       }

    }


     class Task implements Runnable {

         @Override
         public void run() {
             log.info("count:{}", count);
             if (count > 20){
                 throw new IllegalArgumentException("自定义定时任务异常");
             }
         }
     }

}
