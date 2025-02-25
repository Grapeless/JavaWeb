package com.lim.filter;

import com.alibaba.fastjson.JSONObject;
import com.lim.pojo.Result;
import com.lim.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
//@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1.获取请求路径
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String url = httpServletRequest.getRequestURI();
        log.info("请求路径:{}", url);

        //2.判断是否为login--登录请求
        if(url.contains("login")){
            log.info("登录成功");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        //3.否则根据请求头中的token检验jwt令牌的存在性和合法性
        String jwt = httpServletRequest.getHeader("token");
        //存在性,不存在则返回错误信息,否则检查合法性
        if(!StringUtils.hasLength(jwt)){
            log.info("不存在jwt");
            String errorJSON = JSONObject.toJSONString(Result.error("NOT_LOGIN"));
            httpServletResponse.getWriter().write(errorJSON);
            return;
        }
        //合法性,jwt校验抛出异常则返回错误信息
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            log.info("jwt校验失败");
            String errorJSON = JSONObject.toJSONString(Result.error("NOT_LOGIN"));
            httpServletResponse.getWriter().write(errorJSON);
            return;
        }

        //4.放行
        log.info("jwt存在:{},且校验成功",jwt);
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
