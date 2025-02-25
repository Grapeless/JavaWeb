package com.lim.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.lim.pojo.Result;
import com.lim.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        //1.获取请求路径
        String url = httpServletRequest.getRequestURI();
        log.info("请求路径:{}", url);

        //2.判断是否为login--登录请求
        if(url.contains("login")){
            log.info("登录成功");
            return true;
        }

        //3.否则根据请求头中的token检验jwt令牌的存在性和合法性
        String jwt = httpServletRequest.getHeader("token");
        //存在性,不存在则返回错误信息,否则检查合法性
        if(!StringUtils.hasLength(jwt)){
            log.info("不存在jwt");
            String errorJSON = JSONObject.toJSONString(Result.error("NOT_LOGIN"));
            httpServletResponse.getWriter().write(errorJSON);
            return false;
        }
        //合法性,jwt校验抛出异常则返回错误信息
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            log.info("jwt校验失败");
            String errorJSON = JSONObject.toJSONString(Result.error("NOT_LOGIN"));
            httpServletResponse.getWriter().write(errorJSON);
            return false;
        }

        //4.放行
        log.info("jwt存在:{},且校验成功",jwt);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
