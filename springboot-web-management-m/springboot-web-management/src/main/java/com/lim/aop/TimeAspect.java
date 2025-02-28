package com.lim.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class TimeAspect {
    @Around("execution(* com.lim.service.*.*(..))")
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long begin = System.currentTimeMillis();

        //调用原始方法运行
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        log.info("{}方法耗时:{}ms", joinPoint.getSignature(), end - begin);

        return result;
    }
}
























