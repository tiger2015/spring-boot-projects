package com.tiger.springboot.mybatis;

import org.apache.kafka.clients.admin.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @Author Zenghu
 * @Date 2022年11月13日 14:54
 * @Description
 * @Version: 1.0
 **/
public class KafkaTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Map<String, Object> props = new HashMap<>();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.100.6:9092,192.168.100.6:9093");
        AdminClient adminClient = AdminClient.create(props);

        // 创建topic
        NewTopic topic = new NewTopic("t3", 2, (short) 1);
        // CreateTopicsResult topics = adminClient.createTopics(Arrays.asList(topic));


        // list topic
        ListTopicsResult listTopicsResult = adminClient.listTopics();
        Collection<TopicListing> topicListings = listTopicsResult.listings().get();



    }


}
