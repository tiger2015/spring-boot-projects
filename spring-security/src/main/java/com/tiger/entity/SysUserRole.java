package com.tiger.entity;

import java.io.Serializable;

/**
 * @ClassName SysUserRole
 * @Description TODO
 * @Author tiger
 * @Date 2019/11/14 20:57
 * @Version 1.0
 **/
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = -5971146052300511211L;
    private Integer roleId;
    private Integer userId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
