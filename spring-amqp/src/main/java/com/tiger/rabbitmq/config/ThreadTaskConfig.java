package com.tiger.rabbitmq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class ThreadTaskConfig {

    @Bean("sendMessage")
    public ThreadPoolTaskScheduler getAsyncExecutor(){
        ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
        executor.setPoolSize(4);
        executor.setThreadGroupName("SendMessage-");
         return executor;
    }
}
