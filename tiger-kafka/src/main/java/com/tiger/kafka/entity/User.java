package com.tiger.kafka.entity;

import com.esotericsoftware.kryo.serializers.TaggedFieldSerializer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * @Author Zenghu
 * @Date 2022年04月05日 19:20
 * @Description
 * @Version: 1.0
 **/
public class User implements Serializable {
    private static final long serialVersionUID = 7699027042389454196L;
    @TaggedFieldSerializer.Tag(value = 0)
    private Long id;
    @TaggedFieldSerializer.Tag(value = 1)
    private String name;
    @TaggedFieldSerializer.Tag(value = 2)
    private List<String> favorites;
    @TaggedFieldSerializer.Tag(value = 3)
    private double salary;
    @TaggedFieldSerializer.Tag(value = 4)
    private LocalDate birthday;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<String> favorites) {
        this.favorites = favorites;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", favorites=" + favorites +
                ", salary=" + salary +
                ", birthday=" + birthday +
                '}';
    }
}
