package com.zlx.bangbang.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 不要动了
 */

@Slf4j
@Component
public class CorsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String originValue = request.getHeader("Origin");
        log.error("客户端请求的 Origin 字段是 {}", originValue);
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "null");  // 试试替换成 originValue
        response.setHeader("Access-Control-Allow-Methods", "*");
//        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Vary", "Origin");
        response.setHeader("Access-Control-Allow-Headers", "content-type, withcredentials");
//        Cookie[] cookies = request.getCookies();
//        for (Cookie cookie : cookies) {
//            log.error("cookie 为: {}, {}, domain:{}", cookie.getName(), cookie.getValue(), cookie.getDomain());
//        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
