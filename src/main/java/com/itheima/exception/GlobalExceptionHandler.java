package com.itheima.exception;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.MySQLGroupConcat;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    //处理异常
    @ExceptionHandler
    public Result handleException(Exception e){//方法形参中指定能够处理的异常类型
        log.error("程序出错了",e);
        //捕获到异常之后，响应一个标准的Result
        return Result.error("对不起,操作失败,请联系管理员");
    }

    //DuplicateKeyException
    @ExceptionHandler
    public Result handleException(DuplicateKeyException e){
        log.error("程序出错了",e);
        String message = e.getMessage();
        int i=message.indexOf("Duplicate entry");
        String errMsg=message.substring(i);
        String[] arr= errMsg.split(" ");
        String msg=arr[2]+" 已存在";
        return Result.error(msg);
    }

    
}