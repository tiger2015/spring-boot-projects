package com.tiger.spring.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryForever;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.retry.RetryUntilElapsed;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;

/**
 * @Author Zenghu
 * @Date 2022/1/13
 * @Description
 * @Version: 1.0
 **/
@Configuration
@EnableConfigurationProperties(value = {CuratorProperties.class})
@ConditionalOnProperty(prefix = "zookeeper", name = {"nodes"})
@Slf4j
public class ApplicationConfig {

    private CuratorProperties curatorProperties;

    public ApplicationConfig(CuratorProperties curatorProperties) {
        this.curatorProperties = curatorProperties;
    }

    @Bean(initMethod = "start", destroyMethod = "close")
    public CuratorFramework curatorFramework() {
        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder();
        builder.connectString(curatorProperties.getNodes());
        builder.sessionTimeoutMs((int) curatorProperties.getSessionTimeout().toMillis());
        builder.connectionTimeoutMs((int) curatorProperties.getConnectionTimeout().toMillis());
        CuratorProperties.RetryProperties retry = curatorProperties.getRetry();
        RetryPolicy retryPolicy;
        if (!ObjectUtils.isEmpty(retry.getForever())) {
            retryPolicy = new RetryForever(retry.getForever().getRetryInterval());
        } else if (!ObjectUtils.isEmpty(retry.getExponentialBackoff())) {
            CuratorProperties.ExponentialBackoffRetryProperties exponentialBackoff = retry.getExponentialBackoff();
            retryPolicy = new ExponentialBackoffRetry(exponentialBackoff.getBaseSleepTimeMs(), exponentialBackoff.getMaxRetries(), exponentialBackoff.getMaxSleepMs());
        } else if (!ObjectUtils.isEmpty(retry.getTimes())) {
            CuratorProperties.RetryNTimesProperties times = retry.getTimes();
            retryPolicy = new RetryNTimes(times.getN(), times.getSleepMsBetweenRetries());
        } else {
            CuratorProperties.RetryUntilElapsedProperties untilElapsed = retry.getUntilElapsed();
            retryPolicy = new RetryUntilElapsed(untilElapsed.getMaxElapsedTimeMs(), untilElapsed.getSleepMsBetweenRetries());
        }
        builder.retryPolicy(retryPolicy);
        log.info("create curator framework");

        return builder.build();
    }


}
