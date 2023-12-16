package com.tiger.qr.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Zenghu
 * @Date 2022年12月20日 22:10
 * @Description
 * @Version: 1.0
 **/
@Data
public class UserInfo implements Serializable {
    private String name;
    private String password;
}
