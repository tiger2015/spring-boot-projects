package com.tiger.qr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Author Zenghu
 * @Date 2021/10/1 16:14
 * @Description
 * @Version: 1.0
 **/
@Configuration
public class ApplicationConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
