package com.tiger.qr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @Author Zenghu
 * @Date 2022年12月29日 21:46
 * @Description
 * @Version: 1.0
 **/
@Configuration
public class TaskConfig {

    @Bean(name = "asyncTaskExecutor")
    public ThreadPoolTaskExecutor asyncTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(4);
        threadPoolTaskExecutor.setMaxPoolSize(4);
        threadPoolTaskExecutor.setKeepAliveSeconds(120);
        threadPoolTaskExecutor.setQueueCapacity(1024);
        threadPoolTaskExecutor.setThreadNamePrefix("async-");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
