package com.lim.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.lim.pojo.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class UserLoginCheckHandler implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登录成功过的用户其session中的id才为非空,才放行
        //为了和emp的id区分,最后session里:
        //id -- emp.id
        //userId = user.id
        if (request.getSession().getAttribute("userId") != null) {
            //log.info("用户已登录,session id 为:{}",request.getSession().getAttribute("userId"));
            return true;
        }
        //否则拦截并返回错误信息
        String errorJSON = JSONObject.toJSONString(Result.error("NOTLOGIN"));
        //log.info("errorJSON:{},URL:{}",errorJSON,request.getRequestURL());
        response.getWriter().write(errorJSON);
        return false;
    }

}
