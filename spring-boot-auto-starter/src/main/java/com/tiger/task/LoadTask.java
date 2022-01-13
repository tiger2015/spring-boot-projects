package com.tiger.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @Author Zenghu
 * @Date 2021/12/14
 * @Description
 * @Version: 1.0
 **/
public class LoadTask {
    private static final Logger logger = LoggerFactory.getLogger(LoadTask.class);

    private TaskProperties taskProperties;

    public LoadTask(TaskProperties taskProperties) {
        this.taskProperties = taskProperties;
    }

    @Scheduled(fixedRateString = "${task.interval:3000}", initialDelay = 0L)
    public void load() {
        logger.info("load:{}", taskProperties.getConfig());
    }

}
