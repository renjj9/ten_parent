package com.ten.base.controller;

import com.ten.entity.Result;
import com.ten.entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 通用异常类
 */
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value= Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR,e.getMessage());
    }

}
