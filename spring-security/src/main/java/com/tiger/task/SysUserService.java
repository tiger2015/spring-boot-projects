package com.tiger.task;

import com.tiger.dao.SysUserMapper;
import com.tiger.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName SysUserService
 * @Description TODO
 * @Author tiger
 * @Date 2019/11/14 21:04
 * @Version 1.0
 **/
@Service
public class SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    public SysUser selectById(Integer id) {
        return userMapper.selectById(id);
    }

    public SysUser selectByName(String name) {
        return userMapper.selectByName(name);
    }
}
