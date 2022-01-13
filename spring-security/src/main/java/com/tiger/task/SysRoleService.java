package com.tiger.task;

import com.tiger.dao.SysRoleMapper;
import com.tiger.entity.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName SysRoleService
 * @Description TODO
 * @Author tiger
 * @Date 2019/11/14 21:06
 * @Version 1.0
 **/
@Service
public class SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    public SysRole selectById(Integer id) {
        return sysRoleMapper.selectById(id);
    }

    public SysRole selectByName(String name) {
        return sysRoleMapper.selectByName(name);
    }
}
