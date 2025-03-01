package com.lim.config;

import com.lim.interceptor.LoginCheckHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginCheckHandler loginCheckHandler;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckHandler)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/employee/login",
                        "/employee/logout",
                        "/backend/**",
                        "/front/**"
                );
    }
}
