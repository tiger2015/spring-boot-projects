package com.tiger.springboot.mybatis.model;

import java.io.Serializable;

/**
 * @Author: ZengHu
 * @Date: 2020/11/29 22:26
 * @Description:
 * @Version: 1.0
 **/
public class Student implements Serializable {
    private static final long serialVersionUID = -7636271292310455907L;

    private Integer id;
    private String name;
    private Short age;

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

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
