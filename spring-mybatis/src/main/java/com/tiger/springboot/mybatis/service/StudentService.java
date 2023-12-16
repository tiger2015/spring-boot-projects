package com.tiger.springboot.mybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tiger.springboot.mybatis.model.dto.StudentDto;
import com.tiger.springboot.mybatis.model.po.StudentPo;

/**
 * @Author Zenghu
 * @Date 2023年08月15日 22:21
 * @Description
 * @Version: 1.0
 **/
public interface StudentService extends IService<StudentPo> {
    Integer add(StudentDto student);
}
