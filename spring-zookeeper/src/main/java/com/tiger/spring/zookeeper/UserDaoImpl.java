package com.tiger.spring.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

/**
 * @Author Zenghu
 * @Date 2022/3/25
 * @Description
 * @Version: 1.0
 **/
@Repository
@Slf4j
@ConditionalOnBean(value = {CuratorFramework.class})
public class UserDaoImpl {

    @PostConstruct
    public void init() {
        log.info("init UserDaoImpl");
    }

}
