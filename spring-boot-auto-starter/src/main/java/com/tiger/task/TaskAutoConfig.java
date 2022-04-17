package com.tiger.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Zenghu
 * @Date 2021/12/14
 * @Description
 * @Version: 1.0
 **/
@Configuration
@EnableConfigurationProperties(value = {TaskProperties.class})
@ComponentScan(basePackages = {"com.tiger.task"})
public class TaskAutoConfig {
    @Autowired
    private TaskProperties taskProperties;

//    @Bean
//    public LoadTask loadTask() {
//        return new LoadTask(taskProperties);
//    }

}
