package com.tiger.spring.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author Zenghu
 * @Date 2022/1/18
 * @Description
 * @Version: 1.0
 **/
@Component
@Order(100)
@Slf4j
public class MyRunner implements ApplicationRunner {
    @Autowired
    private CuratorFramework curatorFramework;

    @Autowired
    private RetryTest retryTest;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        retryTest.retry();


        new Thread(new Runnable() {
            @Override
            public void run() {
                InterProcessMutex mutex = new InterProcessMutex(curatorFramework, "/lock");
                try {
                    mutex.acquire();
                    log.info("acquire lock");
                    TimeUnit.SECONDS.sleep(60);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        mutex.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


            }
        }).start();

        TimeUnit.SECONDS.sleep(10);
        InterProcessMutex mutex = new InterProcessMutex(curatorFramework, "/lock");


        mutex.acquire();
        log.info("acquire lock======");
        mutex.release();


    }
}
