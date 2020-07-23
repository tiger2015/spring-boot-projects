package com.tiger.redis;

import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.models.partitions.RedisClusterNode;
import io.lettuce.core.models.role.RedisInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Auther: Zeng Hu
 * @Date: 2020/7/21 22:42
 * @Description:
 * @Version: 1.0
 **/
@Component
@Order(999)
@Slf4j
public class MyRunner implements ApplicationRunner {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisProperties properties;

    @Autowired
    private RedisClusterClient redisClusterClient;

    private AtomicLong counter = new AtomicLong(100000);

    private ExecutorService threadPool = Executors.newFixedThreadPool(2048);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        clear();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            threadPool.execute(() -> {
                redisTemplate.opsForValue().set("key" + counter.decrementAndGet(), "1231321");
            });
        }
        while (counter.get() > 0) ;
        long end = System.currentTimeMillis();
        log.info("time:{}", end - start);
        threadPool.shutdown();
    }


    private void clear() {
        StatefulRedisClusterConnection<String, String> connect = redisClusterClient.connect();
        List<RedisClusterNode> partitions =
                connect.getPartitions().getPartitions();
        for (RedisClusterNode redisClusterNode : partitions) {
            if (redisClusterNode.getRole() == RedisInstance.Role.MASTER) {
                log.info("flush db on node:{}", redisClusterNode.getNodeId());
                RedisCommands<String, String> redisCommands =
                        connect.getConnection(redisClusterNode.getNodeId()).sync();
                redisCommands.flushdb();
            }
        }
        connect.close();
    }
}
