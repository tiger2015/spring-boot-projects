package com.tiger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import com.tiger.servlet.VerifyCodeServlet;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableRedisHttpSession
public class SecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }
    @Bean
    public ServletRegistrationBean indexServletRegistration(){
        ServletRegistrationBean registration = new ServletRegistrationBean(new VerifyCodeServlet());
        registration.addUrlMappings("/getVerifyCode");
        return registration;
    }
}
