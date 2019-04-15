package com.zlx.bangbang.config;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
@Profile("prod")
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor())
                .excludePathPatterns("/login/user")
                .excludePathPatterns("/user/register")
                .excludePathPatterns("/test/get")
                .addPathPatterns("/**");
    }

    private AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }
}
