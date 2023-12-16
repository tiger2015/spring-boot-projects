package com.tiger.springboot.mybatis.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Zenghu
 * @Date 2023年08月16日 21:49
 * @Description
 * @Version: 1.0
 **/
@Data
public class StudentDto implements Serializable {
    private static final long serialVersionUID = -7808716039924299474L;
    private Integer id;
    private String name;
    private Integer age;
}
