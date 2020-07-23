package com.tiger.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: Zeng Hu
 * @Date: 2020/7/21 22:07
 * @Description:
 * @Version: 1.0
 **/
@SpringBootApplication
public class RedisApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(RedisApplication.class);
        application.run(args);
    }

}
