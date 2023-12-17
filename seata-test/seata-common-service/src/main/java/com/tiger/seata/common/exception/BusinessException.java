package com.tiger.seata.common.exception;

/**
 * @Author Zenghu
 * @Date 2023年12月17日 15:45
 * @Description
 * @Version: 1.0
 **/
public class BusinessException extends BaseException {
    private static final long serialVersionUID = 4581141359362956088L;

    public BusinessException(int code, String msg) {
        super(code, msg);
    }
}
