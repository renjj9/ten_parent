package com.ten.gathering;

import com.ten.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//@EnableCaching
//@SpringBootApplication
//@ComponentScan(basePackages = {"com.ten.*"})  //指定扫描包路径
//@EnableDiscoveryClient // 开启Eureka客户端
@SpringCloudApplication
public class GApplication {

    public static void main(String[] args) {
        SpringApplication.run(GApplication.class,args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
