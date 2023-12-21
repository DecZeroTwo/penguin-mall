package com.penguin.penguinmall.authority.config;

import com.penguin.penguinmall.authority.interceptor.AuthorityInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class BmsMvcConfig implements WebMvcConfigurer {
    @Autowired
    private AuthorityInterceptor authorityInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorityInterceptor)
                .addPathPatterns("/api/**","*.html")
                .excludePathPatterns("/api/user/login",
                        "/api/user/registry",
                        "/api/user/captcha",
                        "/api/user/emailCaptcha");
    }
}