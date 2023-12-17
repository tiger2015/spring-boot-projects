package com.tiger.seata.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author Zenghu
 * @Date 2023年12月17日 16:40
 * @Description
 * @Version: 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class StockApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockApplication.class, args);
    }
}
