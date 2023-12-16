package com.tiger.springboot.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tiger.springboot.mybatis.dao.StudentMapper;
import com.tiger.springboot.mybatis.model.dto.StudentDto;
import com.tiger.springboot.mybatis.model.po.StudentPo;
import com.tiger.springboot.mybatis.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Zenghu
 * @Date 2023年08月15日 23:24
 * @Description
 * @Version: 1.0
 **/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, StudentPo> implements StudentService {


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer add(StudentDto student) {
        StudentPo studentPo = new StudentPo();
        studentPo.setName(student.getName());
        studentPo.setAge(student.getAge().shortValue());
        save(studentPo);
        return studentPo.getId();
    }
}
