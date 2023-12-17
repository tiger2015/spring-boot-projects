package com.tiger.seata.common.exception;

/**
 * @Author Zenghu
 * @Date 2023年12月17日 15:35
 * @Description
 * @Version: 1.0
 **/
public class BaseException extends Exception {
    private static final long serialVersionUID = 3928227538496774726L;
    private int code;

    public BaseException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public BaseException(int code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
