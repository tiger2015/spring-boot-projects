package com.tiger.consumer.service;

import com.tiger.consumer.entity.User;

public interface UserService {
    boolean add(User user);
    User findUser(String userName);
}
