package com.tiger.spring.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

/**
 * @Author Zenghu
 * @Date 2022年04月05日 16:02
 * @Description
 * @Version: 1.0
 **/
@Component
@Slf4j
public class RetryTest {


    @Retryable(value = IllegalArgumentException.class, backoff = @Backoff(value = 1000), recover = "recover")
    public void retry() {
        log.info("call retry");
        throw new IllegalArgumentException("param error");
    }


    @Recover
    public void recover(IllegalArgumentException e) {
        log.error("exception", e);
    }

}
