package com.tiger.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.DisabledException;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName VerifyCodeFilter
 * @Description TODO
 * @Author zeng.h
 * @Date 2019/11/16 16:53
 * @Version 1.0
 **/
@Slf4j
public class VerifyCodeFilter extends OncePerRequestFilter {
    private static final PathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (isProtectedUrl(request)) {
            String verifyCode = request.getParameter("verifyCode");
            if (!validateVerify(verifyCode)) {
                request.getSession().setAttribute("SPRING_SECURITY_LAST_EXCEPTION", new DisabledException("验证码输入错误"));
                request.getRequestDispatcher("/login/error").forward(request, response);
            } else {
                filterChain.doFilter(request, response);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private boolean validateVerify(String inputVerify) {
        //获取当前线程绑定的request对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 不分区大小写
        // 这个validateCode是在servlet中存入session的名字
        String validateCode = ((String) request.getSession().getAttribute("validateCode")).toLowerCase();
        inputVerify = inputVerify.toLowerCase();
        log.info("验证码:{}，用户输入:{}", validateCode, inputVerify);
        return validateCode.equals(inputVerify);
    }

    // 拦截 /login的POST请求
    private boolean isProtectedUrl(HttpServletRequest request) {
        return "POST".equals(request.getMethod()) && pathMatcher.match("/login", request.getServletPath());
    }
}
