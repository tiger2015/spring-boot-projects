package com.tiger.entity;

import java.io.Serializable;

/**
 * @ClassName SysUser
 * @Description TODO
 * @Author tiger
 * @Date 2019/11/14 20:56
 * @Version 1.0
 **/
public class SysUser implements Serializable {
    private static final long serialVersionUID = -5064976492352584030L;

    private Integer id;
    private String name;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
