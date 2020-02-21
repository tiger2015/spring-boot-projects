package com.tiger.consumer.redis.service;

import com.tiger.consumer.redis.entity.User;

public interface UserService {

    boolean addUser(User user);
    boolean updateUser(User user);
    boolean removeUser(String id);

    User getUser(String id);

}
