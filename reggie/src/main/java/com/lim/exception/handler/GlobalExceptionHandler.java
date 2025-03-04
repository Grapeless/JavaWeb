package com.lim.exception.handler;

import com.lim.exception.ReferentialIntegrityException;
import com.lim.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    //这样，如果发生对应类型的异常则会将这个异常类传入所修饰方法对应的异常类中
    //Unique Column 约束异常
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
        e.printStackTrace();
        if (e.getMessage().contains("Duplicate entry")) {
            //log.info("errorMsg:{}",e.getMessage());
            return Result.error(e.getMessage().split(" ")[2] + "已存在");
        }
        return Result.error("未知错误");
    }

    //Referential Integrity 参照完整性约束异常
    @ExceptionHandler(ReferentialIntegrityException.class)
    public Result handleReferentialIntegrityException(ReferentialIntegrityException e){

        return Result.error(e.getMessage());
    }
}
