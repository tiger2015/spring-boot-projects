package com.tiger.seata.common.ret;

import java.io.Serializable;

/**
 * @Author Zenghu
 * @Date 2023年12月17日 15:31
 * @Description
 * @Version: 1.0
 **/
public class R<T> implements Serializable {
    private static final long serialVersionUID = 9005928106685771418L;
    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public static R<Void> success() {
        R<Void> ret = new R<>();
        ret.code = 200;
        ret.msg = "成功";
        return ret;
    }

    public static <D> R<D> success(D data) {
        R<D> ret = new R<>();
        ret.code = 200;
        ret.msg = "成功";
        ret.data = data;
        return ret;
    }

    public static R<Void> fail() {
        R<Void> ret = new R<>();
        ret.code = 400;
        ret.msg = "失败";
        return ret;
    }

    public static R<Void> fail(int code, String msg) {
        R<Void> ret = new R<>();
        ret.code = code;
        ret.msg = msg;
        return ret;
    }
}

