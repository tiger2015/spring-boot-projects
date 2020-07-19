package com.tiger.consumer.redis.service;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.tiger.consumer.redis.dao.UserDao;
import com.tiger.consumer.redis.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tiger.consumer.redis.util.Object2Map;

import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean addUser(User user) {
        try {
            Preconditions.checkNotNull(user, "用户为空");
            Map<String, Object> map = Object2Map.object2Map(user);
            return userDao.addUser(user.getName(), map);
        } catch (Exception e) {
            log.error("add user error", e);
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        try {
            Preconditions.checkNotNull(user, "用户为空");
            if (Strings.isNullOrEmpty(user.getName())) {
                return false;
            }
            Map<String, Object> map = Object2Map.object2Map(user);
            return userDao.updateUser(user.getName(), map);
        } catch (Exception e) {
            log.error("update user error", e);
        }
        return false;
    }

    @Override
    public boolean removeUser(String id) {
        if (Strings.isNullOrEmpty(id)) {
            return false;
        }
        return userDao.deleteUser(id);
    }

    @Override
    public User getUser(String id) {
        if (Strings.isNullOrEmpty(id)) {
            return null;
        }
        try {
            Map<Object, Object> user = userDao.getUser(id);
            return Object2Map.map2Object(user, User.class);
        } catch (Exception e) {
            log.error("get user error", e);
        }
        return null;
    }
}
