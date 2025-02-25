package com.lim.aop;

import com.alibaba.fastjson.JSONObject;
import com.lim.mapper.OperateLogMapper;
import com.lim.pojo.OperateLog;
import com.lim.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogAspect {
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Around("@annotation(com.lim.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {

        //operateUser
        String jwt = httpServletRequest.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer operateUser = (Integer) claims.get("id");

        //operateTime
        LocalDateTime operateTime = LocalDateTime.now();

        //className
        String className = joinPoint.getTarget().getClass().getName();

        //methodName
        String methodName = joinPoint.getSignature().getName();

        //methodParams
        String methodParams = Arrays.toString(joinPoint.getArgs());

        long begin = System.currentTimeMillis();
        Object res =  joinPoint.proceed();
        //returnValue
        String returnValue = JSONObject.toJSONString(res);
        long end = System.currentTimeMillis();

        //costTime
        long costTime = end - begin;

        OperateLog operateLog = new OperateLog(null, operateUser, operateTime, className, methodName, methodParams, returnValue, costTime);
        operateLogMapper.insert(operateLog);

        log.info("日志数据{}",operateLog);
        return res;
    }
}
