package com.tiger.entity;

import java.io.Serializable;

/**
 * @ClassName SysRole
 * @Description TODO
 * @Author tiger
 * @Date 2019/11/14 20:57
 * @Version 1.0
 **/
public class SysRole implements Serializable {
    private static final long serialVersionUID = 3233435756412753944L;

    private Integer id;

    private String name;

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
}
