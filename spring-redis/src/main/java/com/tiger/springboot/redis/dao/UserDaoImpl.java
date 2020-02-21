package com.tiger.springboot.redis.dao;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    private static final String USER_KEY = "user:%s:hash";

    @Resource(name = "template")
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean addUser(String id, Map<String, Object> user) {

        try {
            redisTemplate.opsForHash().putAll(String.format(USER_KEY, id), user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateUser(String id, Map<String, Object> user) {
        try {
            redisTemplate.opsForHash().putAll(String.format(USER_KEY, id), user);
            return true;
        } catch (Exception ex) {
        }
        return false;
    }

    @Override
    public boolean deleteUser(String id) {
        return redisTemplate.delete(String.format(USER_KEY, id));
    }

    @Override
    public Map<Object, Object> getUser(String id) {

        try {
            return redisTemplate.opsForHash().entries(String.format(USER_KEY, id));
        } catch (Exception e) {
        }
        return null;
    }
}
