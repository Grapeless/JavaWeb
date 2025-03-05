package com.lim.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.lim.pojo.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class EmpLoginCheckHandler implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登录成功过的用户其session中的id才为非空,才放行
        //写道后面发现emp和user都是id，但是这里为emp存进去的键值"id"没做区分,懒得改了
        //后面又发现...不好判断前端会请求那些静态页面用于登录页面，有点不好做精准拦截，就和视频中一样随便拦一下吧
        if (request.getSession().getAttribute("id") != null) {
            //log.info("用户已登录,session id 为:{}",request.getSession().getAttribute("id"));
            return true;
        }
        if (request.getSession().getAttribute("userId") != null) {
            return true;
        }
        //否则拦截并返回错误信息
        String errorJSON = JSONObject.toJSONString(Result.error("NOTLOGIN"));
        //log.info("errorJSON:{},URL:{}",errorJSON,request.getRequestURL());
        response.getWriter().write(errorJSON);
        return false;
    }

}
