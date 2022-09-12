package com.tiger.qr.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author Zenghu
 * @Date 2022年05月01日 17:52
 * @Description
 * @Version: 1.0
 **/
@Slf4j
@Component
@Order(999)
public class UserDaoB {


    @PostConstruct
    public void init() {
        log.info("=============init UserDaoB");

    }

}
