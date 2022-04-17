package com.tiger.spring.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @Author Zenghu
 * @Date 2022/3/25
 * @Description
 * @Version: 1.0
 **/
@Service
@ConditionalOnBean(value = {UserDaoImpl.class})
@Slf4j
public class UserServiceImpl {

    private final UserDaoImpl userDao;

    public UserServiceImpl(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    @PostConstruct
    public void init() {
        log.info("init UserServiceImpl:{}", userDao == null);
    }
}
