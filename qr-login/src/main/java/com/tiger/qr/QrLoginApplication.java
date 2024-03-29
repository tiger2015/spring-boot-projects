package com.tiger.qr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Author Zenghu
 * @Date 2021/10/1 15:15
 * @Description
 * @Version: 1.0
 **/
@SpringBootApplication
@EnableAsync
@EnableCaching
public class QrLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(QrLoginApplication.class, args);
    }

}
