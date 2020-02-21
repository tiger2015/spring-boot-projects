package com.tiger.springboot.redis.service;

import com.tiger.springboot.redis.entity.User;

public interface UserService {

    boolean addUser(User user);
    boolean updateUser(User user);
    boolean removeUser(String id);

    User getUser(String id);

}
