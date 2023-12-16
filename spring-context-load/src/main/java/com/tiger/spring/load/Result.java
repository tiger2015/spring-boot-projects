package com.tiger.spring.load;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author Zenghu
 * @Date 2023年02月02日 22:55
 * @Description
 * @Version: 1.0
 **/
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -5296347480573329595L;
    private int code;
    private String message;
    private T data;

    private Result(int code, String message) {
        this(code, message, null);
    }

    private Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> success() {
        return new Result<>(200, "success");
    }

    public static <T> Result<T> success(int code, String message) {
        return new Result<>(code, message);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(200, "success", data);
    }

    public static <T> Result<T> success(int code, String message, T data) {
        return new Result<>(code, message, data);
    }

    public static <T> Result<T> error(int code, String message) {
        return new Result<>(code, message);
    }

    public static <T> Result<T> error(T data) {
        return new Result<>(400, "fail", data);
    }
}
