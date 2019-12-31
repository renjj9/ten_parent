package com.ten.client;

import com.ten.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "ten-article") // 标注该类是一个feign接口
public interface UserClient {
    // 两个坑：1. @GetMapping不支持   2. @PathVariable得设置value
    @RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
    Result findById(@PathVariable(value = "id") String id);
}
