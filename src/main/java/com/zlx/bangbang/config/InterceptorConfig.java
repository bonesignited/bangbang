package com.zlx.bangbang.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor())
                .excludePathPatterns("/login/user")
                .excludePathPatterns("/user/register")
                .addPathPatterns("/**");
    }

    private AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }
}
