package com.tiger.consumer.redis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    private static final String USER_KEY = "user:%s:hash";

    @Autowired()
    @Qualifier(value = "template")
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean addUser(String id, Map<String, Object> user) {
        redisTemplate.opsForHash().putAll(String.format(USER_KEY, id), user);
        return true;
    }

    @Override
    public boolean updateUser(String id, Map<String, Object> user) {
        redisTemplate.opsForHash().putAll(String.format(USER_KEY, id), user);
        return true;
    }

    @Override
    public boolean deleteUser(String id) {
        return redisTemplate.delete(String.format(USER_KEY, id));
    }

    @Override
    public Map<Object, Object> getUser(String id) {
        return redisTemplate.opsForHash().entries(String.format(USER_KEY, id));
    }
}
