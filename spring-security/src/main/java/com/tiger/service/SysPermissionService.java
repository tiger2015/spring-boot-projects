package com.tiger.service;

import com.tiger.dao.SysPermissionMapper;
import com.tiger.entity.SysPersmission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SysPermissionService
 * @Description TODO
 * @Author tiger
 * @Date 2019/11/18 22:22
 * @Version 1.0
 **/
@Service
public class SysPermissionService {
    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    public List<SysPersmission> listByRoleId(Integer roleId) {
        return sysPermissionMapper.listByRoleId(roleId);
    }
}
