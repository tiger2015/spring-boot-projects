package com.tiger.spring.load.App1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author Zenghu
 * @Date 2022年06月11日 21:50
 * @Description
 * @Version: 1.0
 **/
@SpringBootApplication
public class SpringApp {

    private static ConfigurableApplicationContext context;

    public static void run(String[] args) {
        context = SpringApplication.run(SpringApp.class, args);
    }


    public static void stop() {
        context.stop();
    }

}
