package com.ten.client;

import com.ten.entity.Result;
import com.ten.entity.StatusCode;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallback implements UserClient {
    @Override
    public Result findById(String id) {
        System.out.println("fallback....");
        return new Result(true, StatusCode.OK, "查询成功", "服务器繁忙，等会再来试试！");
    }
}
