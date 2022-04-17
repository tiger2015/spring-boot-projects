package com.tiger.spring.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @Author Zenghu
 * @Date 2022/1/14
 * @Description
 * @Version: 1.0
 **/
@SpringBootApplication
@EnableRetry
@Slf4j
public class CuratorApplication {


    static {
        System.setProperty("zookeeper.sasl.client", "false");
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CuratorApplication.class, args);

        CuratorFramework curatorFramework = context.getBean(CuratorFramework.class);
        CuratorFrameworkState state = curatorFramework.getState();
        if (state == CuratorFrameworkState.STARTED){
            log.info("start");
        }

    }

}
