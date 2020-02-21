package com.tiger.springboot.redis.service;

import com.tiger.springboot.redis.dao.UserDao;
import com.tiger.springboot.redis.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.Object2Map;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean addUser(User user) {
        try {
            if (user == null) {
                throw new NullPointerException();
            }
            Map<String, Object> map = Object2Map.object2Map(user);
            return userDao.addUser(user.getName(), map);
        } catch (Exception e) {

        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean removeUser(String id) {
        return false;
    }

    @Override
    public User getUser(String id) {
        Map<Object, Object> user = userDao.getUser(id);
        try {
            return Object2Map.map2Object(user,User.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
