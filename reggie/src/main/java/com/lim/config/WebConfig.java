package com.lim.config;

import com.lim.interceptor.EmpLoginCheckHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final EmpLoginCheckHandler empLoginCheckHandler;

    public WebConfig(EmpLoginCheckHandler empLoginCheckHandler){
        this.empLoginCheckHandler = empLoginCheckHandler;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(empLoginCheckHandler)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/employee/login",
                        "/employee/logout",
                        "/backend/**",
                        "/front/**",
                        "/user/sendMsg",
                        "/user/login"
                );
    }
}
