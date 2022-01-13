package com.tiger.qr.service;

/**
 * @Author Zenghu
 * @Date 2021/10/1 16:19
 * @Description
 * @Version: 1.0
 **/
public interface UserService {
    String bindUserIdAndToken(Integer userId, String token) throws Exception;
}
