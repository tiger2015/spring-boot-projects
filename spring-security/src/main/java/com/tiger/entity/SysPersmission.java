package com.tiger.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName SysPersmission
 * @Description TODO
 * @Author tiger
 * @Date 2019/11/18 22:16
 * @Version 1.0
 **/
public class SysPersmission implements Serializable {
    private static final long serialVersionUID = 7899334212218324043L;
    private Integer id;
    private String url;
    private String permission;
    private Integer roleId;
    private List<String> permissions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<String> getPermissions() {
        return Arrays.asList(this.permission.trim().split(","));
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}
