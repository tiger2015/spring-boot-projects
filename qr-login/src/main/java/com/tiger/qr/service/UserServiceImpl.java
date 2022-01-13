package com.tiger.qr.service;


import com.google.gson.Gson;
import com.tiger.qr.websocket.WebSocketServer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Zenghu
 * @Date 2021/10/1 16:20
 * @Description
 * @Version: 1.0
 **/
@Service
public class UserServiceImpl implements UserService {
    private Gson gson = new Gson();

    @Override
    public String bindUserIdAndToken(Integer userId, String token) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "ok");
        map.put("userId", userId);
        String s = gson.toJson(map);
        WebSocketServer.sendInfo(s, token);
        return null;
    }
}
