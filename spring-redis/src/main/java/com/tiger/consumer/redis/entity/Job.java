package com.tiger.consumer.redis.entity;

import java.io.Serializable;

/**
 * @Author Zenghu
 * @Date 2022/1/8
 * @Description
 * @Version: 1.0
 **/
public class Job implements Serializable {
    private static final long serialVersionUID = -8072537836163061279L;
    private String name;
    private Double salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
