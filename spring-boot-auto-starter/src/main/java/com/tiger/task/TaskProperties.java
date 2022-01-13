package com.tiger.task;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author Zenghu
 * @Date 2021/12/14
 * @Description
 * @Version: 1.0
 **/
@ConfigurationProperties(prefix = "task")
public class TaskProperties {
    private String config;
    private int interval;

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
