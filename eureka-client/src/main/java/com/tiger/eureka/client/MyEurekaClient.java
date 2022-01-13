package com.tiger.eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableEurekaClient
public class MyEurekaClient {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(MyEurekaClient.class);
        application.run(args);
    }
}
