package com.wnhz.older.authority.config;

import com.wnhz.older.authority.interceptor.AuthorityInterceptor;
import com.wnhz.older.authority.interceptor.BlackListInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Date;

@Configuration
@Slf4j
public class BmsMvcConfig implements WebMvcConfigurer {
    @Autowired
    private AuthorityInterceptor authorityInterceptor;
    @Autowired
    private BlackListInterceptor blackListInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorityInterceptor)
                .addPathPatterns("/api/**","*.html")
                .excludePathPatterns("/api/user/login",
                        "/api/user/register",
                        "/api/user/captcha",
                        "/api/user/emailCaptcha",
                        "/PerInsufficient.html",
                        "/",
                        "/error",
                        "/index.html",
                        "/img/PerInsufficient.png",
                        "/blackList.html",
                        "/img/blackList.jpg");
        registry.addInterceptor(blackListInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/blackList.html",
                        "/img/blackList.png");
    }
}
