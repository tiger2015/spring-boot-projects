package com.tiger.consumer.redis.service;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.tiger.consumer.redis.dao.UserDao;
import com.tiger.consumer.redis.entity.User;
import com.tiger.consumer.redis.util.Object2MapUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean addUser(User user) {
        Preconditions.checkNotNull(user, "用户为空");
        Map<String, Object> map = Object2MapUtil.objectToMapByJson(user, User.class);
        return userDao.addUser(user.getName(), map);
    }

    @Override
    public boolean updateUser(User user) {
        Preconditions.checkNotNull(user, "用户为空");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(user.getName()), "用户名为空");
        Map<String, Object> map = Object2MapUtil.objectToMapByJson(user, User.class);
        return userDao.updateUser(user.getName(), map);
    }

    @Override
    public boolean removeUser(String id) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(id), "id为空");
        return userDao.deleteUser(id);
    }

    @Override
    public User getUser(String id) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(id), "id为空");
        Map<Object, Object> user = userDao.getUser(id);
        return Object2MapUtil.mapToObjectByJson(user, User.class);
    }
}
