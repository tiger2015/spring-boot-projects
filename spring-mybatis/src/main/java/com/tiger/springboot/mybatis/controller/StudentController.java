package com.tiger.springboot.mybatis.controller;

import com.tiger.springboot.mybatis.model.dto.StudentDto;
import com.tiger.springboot.mybatis.model.po.StudentPo;
import com.tiger.springboot.mybatis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Zenghu
 * @Date 2023年08月15日 23:17
 * @Description
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/student/api")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @PostMapping("/add")
    public Integer add(@RequestBody StudentDto student) {
       return studentService.add(student);
    }
}
