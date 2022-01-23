package com.tiger.spring.zookeeper;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * @Author Zenghu
 * @Date 2022/1/13
 * @Description
 * @Version: 1.0
 **/
@ConfigurationProperties(prefix = "zookeeper")
@Configuration
public class CuratorProperties {

    private String nodes;
    private Duration sessionTimeout;
    private Duration connectionTimeout;
    private RetryProperties retry;

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }

    public Duration getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(Duration sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public Duration getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(Duration connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public RetryProperties getRetry() {
        return retry;
    }

    public void setRetry(RetryProperties retry) {
        this.retry = retry;
    }

    public static class RetryProperties {
        private ExponentialBackoffRetryProperties exponentialBackoff;
        private RetryForeverProperties forever;
        private RetryNTimesProperties times;
        private RetryUntilElapsedProperties untilElapsed;

        public RetryProperties() {
        }

        public ExponentialBackoffRetryProperties getExponentialBackoff() {
            return exponentialBackoff;
        }

        public void setExponentialBackoff(ExponentialBackoffRetryProperties exponentialBackoff) {
            this.exponentialBackoff = exponentialBackoff;
        }

        public RetryForeverProperties getForever() {
            return forever;
        }

        public void setForever(RetryForeverProperties forever) {
            this.forever = forever;
        }

        public RetryNTimesProperties getTimes() {
            return times;
        }

        public void setTimes(RetryNTimesProperties times) {
            this.times = times;
        }

        public RetryUntilElapsedProperties getUntilElapsed() {
            return untilElapsed;
        }

        public void setUntilElapsed(RetryUntilElapsedProperties untilElapsed) {
            this.untilElapsed = untilElapsed;
        }
    }


    public static class ExponentialBackoffRetryProperties {
        private int baseSleepTimeMs; // 第一次sleep的时间
        private int maxRetries; // 重试次数最多为29
        private int maxSleepMs = Integer.MAX_VALUE; // 每次重试的最大sleep时间，默认为Integer.MAX_INT

        public int getBaseSleepTimeMs() {
            return baseSleepTimeMs;
        }

        public void setBaseSleepTimeMs(int baseSleepTimeMs) {
            this.baseSleepTimeMs = baseSleepTimeMs;
        }

        public int getMaxRetries() {
            return maxRetries;
        }

        public void setMaxRetries(int maxRetries) {
            this.maxRetries = maxRetries;
        }

        public int getMaxSleepMs() {
            return maxSleepMs;
        }

        public void setMaxSleepMs(int maxSleepMs) {
            this.maxSleepMs = maxSleepMs;
        }
    }

    public static class RetryForeverProperties {
        private int retryInterval;

        public int getRetryInterval() {
            return retryInterval;
        }

        public void setRetryInterval(int retryInterval) {
            this.retryInterval = retryInterval;
        }
    }

    public static class RetryNTimesProperties {
        private int n;
        private int sleepMsBetweenRetries;

        public int getN() {
            return n;
        }

        public void setN(int n) {
            this.n = n;
        }

        public int getSleepMsBetweenRetries() {
            return sleepMsBetweenRetries;
        }

        public void setSleepMsBetweenRetries(int sleepMsBetweenRetries) {
            this.sleepMsBetweenRetries = sleepMsBetweenRetries;
        }
    }

    public static class RetryUntilElapsedProperties {
        private int maxElapsedTimeMs;
        private int sleepMsBetweenRetries;

        public int getMaxElapsedTimeMs() {
            return maxElapsedTimeMs;
        }

        public void setMaxElapsedTimeMs(int maxElapsedTimeMs) {
            this.maxElapsedTimeMs = maxElapsedTimeMs;
        }

        public int getSleepMsBetweenRetries() {
            return sleepMsBetweenRetries;
        }

        public void setSleepMsBetweenRetries(int sleepMsBetweenRetries) {
            this.sleepMsBetweenRetries = sleepMsBetweenRetries;
        }
    }


}
