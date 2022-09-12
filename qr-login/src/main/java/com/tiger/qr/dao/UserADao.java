package com.tiger.qr.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author Zenghu
 * @Date 2022年05月01日 17:51
 * @Description
 * @Version: 1.0
 **/
@Component
@Order(1000)
@Slf4j
public class UserADao {


    @PostConstruct
    public void init() {
        log.info(">>>>>>>>>>init UserADao");
    }

}
