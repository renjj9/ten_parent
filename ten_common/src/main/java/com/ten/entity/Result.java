package com.ten.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result<T> {
    private boolean flag;     
    private Integer code;     
    private String message;     
    private Object data;

    public Result(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }
}
