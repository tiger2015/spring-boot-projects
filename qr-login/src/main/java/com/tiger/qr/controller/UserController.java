package com.tiger.qr.controller;

import com.tiger.qr.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * @Author Zenghu
 * @Date 2022年12月20日 22:09
 * @Description
 * @Version: 1.0
 **/
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(UserInfo userInfo) {
        log.info("user:{} login", userInfo.getName());
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = requestAttributes.getRequest().getSession();
        log.info("session:{}", session.getId());
        return "success";
    }

}
