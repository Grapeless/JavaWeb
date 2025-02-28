package com.lim.exception;

import com.lim.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public Result exc(Exception exception){
        exception.printStackTrace();
        return Result.error("对不起，操作失败");
    }
}
