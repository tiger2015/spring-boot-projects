package com.tiger.springboot.mybatis.model;

import java.io.Serializable;

/**
 * @Author: ZengHu
 * @Date: 2020/11/29 13:58
 * @Description:
 * @Version: 1.0
 **/
public class ConsumptionType implements Serializable {

    private static final long serialVersionUID = 4584921933937842411L;

    private Integer id;
    private String name;
    private String desc;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
