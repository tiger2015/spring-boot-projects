package com.tiger.consumer.redis.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;

/**
 * @Author Zenghu
 * @Date 2021/10/8 22:46
 * @Description
 * @Version: 1.0
 **/
@Slf4j
public class RedisMessageListener implements StreamListener<String, ObjectRecord<String, String>> {


    @Override
    public void onMessage(ObjectRecord<String, String> stringStringObjectRecord) {

       log.info("receive:{}", stringStringObjectRecord.toString());




    }
}
