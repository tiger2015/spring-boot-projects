package com.tiger.spring.load.App1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @Author Zenghu
 * @Date 2022年06月11日 21:49
 * @Description
 * @Version: 1.0
 **/
@Configuration
@Slf4j
public class AppConfig {

    @PostConstruct
    public void init(){
        log.info("init AppConfig");
    }

}
