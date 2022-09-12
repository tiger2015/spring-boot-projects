package com.tiger.qr;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author Zenghu
 * @Date 2022年05月01日 18:04
 * @Description
 * @Version: 1.0
 **/
@Slf4j
@Component
@Order(1001)
public class MyApplicationRunnerB implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Runner B ================");
    }
}
