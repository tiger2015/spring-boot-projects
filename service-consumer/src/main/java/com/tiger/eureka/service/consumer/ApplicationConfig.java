package com.tiger.eureka.service.consumer;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: ZengHu
 * @Date: 2020/12/2 23:11
 * @Description:
 * @Version: 1.0
 **/
@Configuration
public class ApplicationConfig {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
