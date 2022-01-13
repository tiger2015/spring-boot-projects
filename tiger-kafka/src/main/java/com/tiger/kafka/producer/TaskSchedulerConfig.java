package com.tiger.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.task.TaskSchedulingProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * @Auther: Zeng Hu
 * @Date: 2020/7/19 16:31
 * @Description:
 * @Version: 1.0
 **/
@Configuration
@Profile("producer")
@EnableScheduling
@EnableAsync
@Slf4j
public class TaskSchedulerConfig implements SchedulingConfigurer {

    @Autowired
    private TaskSchedulingProperties taskSchedulingProperties;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(getThreadPoolTaskScheduler());
        log.info("configure task");
    }

    public ThreadPoolTaskScheduler getThreadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(taskSchedulingProperties.getPool().getSize());
        threadPoolTaskScheduler.setThreadNamePrefix(taskSchedulingProperties.getThreadNamePrefix());
        threadPoolTaskScheduler.setAwaitTerminationSeconds((int) taskSchedulingProperties.getShutdown().getAwaitTerminationPeriod().getSeconds());
        threadPoolTaskScheduler.setWaitForTasksToCompleteOnShutdown(taskSchedulingProperties.getShutdown().isAwaitTermination());
        threadPoolTaskScheduler.afterPropertiesSet();
        return threadPoolTaskScheduler;
    }
}
