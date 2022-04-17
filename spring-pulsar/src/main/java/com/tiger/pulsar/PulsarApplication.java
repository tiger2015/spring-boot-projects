package com.tiger.pulsar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author Zenghu
 * @Date 2022/3/20
 * @Description
 * @Version: 1.0
 **/
@SpringBootApplication
@EnableScheduling
@EnableAsync
public class PulsarApplication {

    public static void main(String[] args) {
        SpringApplication.run(PulsarApplication.class, args);
    }

}
