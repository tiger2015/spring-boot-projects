package com.tiger.consumer.redis.dao;

import java.util.Map;

public interface UserDao {

    boolean addUser(String id, Map<String, Object> user);

    boolean updateUser(String id, Map<String, Object> user);

    boolean deleteUser(String id);

    Map<Object,Object> getUser(String id);
}
