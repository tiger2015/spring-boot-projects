package com.tiger.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: Zeng Hu
 * @Date: 2020/7/18 20:54
 * @Description:
 * @Version: 1.0
 **/
@SpringBootApplication
public class KafkaApplication {

    static {
        System.setProperty("log4j2.contextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
    }

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(KafkaApplication.class);
        application.run(args);
    }
}
