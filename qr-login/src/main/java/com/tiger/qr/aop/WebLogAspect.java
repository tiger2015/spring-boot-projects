package com.tiger.qr.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Author Zenghu
 * @Date 2021/10/1 15:35
 * @Description
 * @Version: 1.0
 **/
@Component
@Aspect
public class WebLogAspect {

    private static final Logger LOG = LoggerFactory.getLogger(WebLogAspect.class);


    // 定义切点
    @Pointcut(value = "execution(public * com.tiger.qr.controller.*.*(..))")
    public void controllerLog() {
    }


    @Before("controllerLog()")
    public void logBeforeController(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        LOG.info("===========request===========");
        LOG.info("==== remote:{}", request.getRemoteHost());
        LOG.info("==== url:{}", request.getRequestURL().toString());
        LOG.info("==== params:{}", Arrays.toString(joinPoint.getArgs()));
    }
}
