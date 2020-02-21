package com.tiger.consumer.service;

import com.tiger.consumer.dao.UserDao;
import com.tiger.consumer.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean add(User user) {
        return userDao.insert(user);
    }

    @Override
    public User findUser(String userName) {
        return userDao.select(userName);
    }
}
