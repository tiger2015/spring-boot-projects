package com.tiger.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author tiger
 * @Date 2019/11/13 22:20
 * @Version 1.0
 **/
@Controller
@Slf4j
public class LoginController {

    @Autowired
    private SessionRegistry sessionRegistry;

    @RequestMapping(value = "/login")
    public String login() {
        return "login.html";
    }

    @RequestMapping(value = "/login/invalid")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public String invalid() {
        return "session timeout";
    }


    @RequestMapping(value = "/")
    public String home() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("登录用户:{}", name);
        return "home.html";
    }

    @RequestMapping("/login/error")
    public void loginError(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        AuthenticationException exception =
                (AuthenticationException) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        try {
            response.getWriter().write(exception.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("/admin")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String printAdmin() {
        return "ADMIN 角色";
    }

    @RequestMapping("/user")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    public String printTest() {
        return "USER 角色";
    }


    @RequestMapping("/admin/c")
    @ResponseBody
    @PreAuthorize(value = "hasPermission('/admin','c')")
    public String admin() {
        return "has permission for /admin c";
    }


    @GetMapping("/kick")
    @ResponseBody
    public String removeSessionByUserName(@RequestParam String userName) {
        int count = 0;
        List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        for (Object user : allPrincipals) {
            if (user instanceof User) {
                String principalName = ((User) user).getUsername();
                if (principalName.equals(userName)) {
                    List<SessionInformation> allSessions = sessionRegistry.getAllSessions(user, false);
                    if (allSessions != null && allSessions.size() > 0) {
                        for (SessionInformation sessionInformation : allSessions) {
                            sessionInformation.expireNow();
                            count++;
                        }
                    }
                }
            }
        }
        return "操作成功，一共清理" + count + "个session";
    }

}
