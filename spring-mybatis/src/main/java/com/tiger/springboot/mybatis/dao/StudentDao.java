package com.tiger.springboot.mybatis.dao;

import com.tiger.springboot.mybatis.model.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: ZengHu
 * @Date: 2020/11/29 22:25
 * @Description:
 * @Version: 1.0
 **/
@Mapper
public interface StudentDao {
    Student selectById(Integer id);

    int insert(Student student);

    int update(Student student);
}
