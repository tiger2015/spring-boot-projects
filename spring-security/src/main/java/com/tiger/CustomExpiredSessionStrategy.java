package com.tiger;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName CustomExpiredSessionStrategy
 * @Description TODO
 * @Author zeng.h
 * @Date 2019/11/20 9:31
 * @Version 1.0
 **/
public class CustomExpiredSessionStrategy implements SessionInformationExpiredStrategy {
    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "已在另一台机器登录，您被迫下线." + event.getSessionInformation().getLastRequest());
        String s = mapper.writeValueAsString(map);
        event.getResponse().setContentType("application/json;charset=UTF-8");
        event.getResponse().getWriter().write(s);
    }
}
