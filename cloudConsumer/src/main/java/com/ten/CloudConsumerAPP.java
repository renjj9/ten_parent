package com.ten;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ten.*"})  //指定扫描包路径
@EnableFeignClients // 开启feign客户端
public class CloudConsumerAPP {
    public static void main(String[] args) {
        SpringApplication.run(CloudConsumerAPP.class, args);
    }
}
