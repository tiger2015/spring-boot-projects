package com.tiger.springboot.mybatis.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @Author: ZengHu
 * @Date: 2020/11/29 22:26
 * @Description:
 * @Version: 1.0
 **/
@TableName("student")
public class StudentPo implements Serializable {
    private static final long serialVersionUID = -7636271292310455907L;

    @TableId(type = IdType.AUTO)
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
