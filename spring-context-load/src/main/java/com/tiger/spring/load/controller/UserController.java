package com.tiger.spring.load.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author Zenghu
 * @Date 2023年02月02日 23:23
 * @Description
 * @Version: 1.0
 **/
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @GetMapping("/list")
    public String listUser(HttpServletRequest servletRequest) {
        HttpSession session = servletRequest.getSession();
        log.info("session id:{}", session.getId());
        return "list user";
    }
}
