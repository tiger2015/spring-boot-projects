package com.tiger.service;

import com.tiger.dao.SysUserRoleMapper;
import com.tiger.entity.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SysUserRoleService
 * @Description TODO
 * @Author tiger
 * @Date 2019/11/14 21:07
 * @Version 1.0
 **/
@Service
public class SysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    public List<SysUserRole> listByUserId(Integer userId){
        return sysUserRoleMapper.listByUserId(userId);
    }

}
