package com.tiger.task;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author Zenghu
 * @Date 2021/12/14
 * @Description
 * @Version: 1.0
 **/
@Component
@Slf4j
public class LoadTask {
    private static final Logger logger = LoggerFactory.getLogger(LoadTask.class);

    private TaskProperties taskProperties;


    public LoadTask(TaskProperties taskProperties) {
        this.taskProperties = taskProperties;
    }

    @PostConstruct
    public void init() {
        log.info("init load task");
    }

    @Scheduled(fixedRateString = "${task.interval:3000}", initialDelay = 0L)
    public void load() {
        logger.info("load:{}", taskProperties.getConfig());
    }

}
