package com.tiger.qr;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author Zenghu
 * @Date 2022年05月01日 18:01
 * @Description
 * @Version: 1.0
 **/
@Order(1000)
@Component
@Slf4j
public class MyApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Runner A ===============");
        while (true){
            TimeUnit.SECONDS.sleep(1000);
        }
    }
}
