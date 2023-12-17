package com.tiger.seata.common.ctl;

import com.tiger.seata.common.exception.BusinessException;
import com.tiger.seata.common.ret.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author Zenghu
 * @Date 2023年12月17日 15:29
 * @Description
 * @Version: 1.0
 **/
@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvice {
    @ExceptionHandler(value = BusinessException.class)
    public R<Void> businessExceptionHandler(BusinessException ex) {
        log.error(ex.getMessage(), ex.getCause());
        return R.fail(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public R<Void> exceptionHandler(Exception ex) {
        log.error(ex.getMessage(), ex.getCause());
        return R.fail(400, ex.getMessage());
    }
}
