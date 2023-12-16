package com.tiger.qr.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author Zenghu
 * @Date 2022年12月20日 22:19
 * @Description
 * @Version: 1.0
 **/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        log.error("occur exception", e);
        return "error";
    }
}
