package com.tiger.spring.load.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Zenghu
 * @Date 2023年02月04日 9:11
 * @Description
 * @Version: 1.0
 **/
@Component
public class CorsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        String origin = servletRequest.getHeader(HttpHeaders.ORIGIN);
        if (StringUtils.isEmpty(origin)) {
            origin = servletRequest.getHeader(HttpHeaders.REFERER);
        }
        servletResponse.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, origin);
        servletResponse.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
        servletResponse.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET,PUT,POST,OPTIONS,DELETE");
        servletResponse.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "*");
        chain.doFilter(request, response);
    }
}
