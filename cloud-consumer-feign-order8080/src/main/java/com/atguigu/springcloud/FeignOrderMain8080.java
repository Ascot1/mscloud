package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author chendm
 * @create 2021-08-15 23:28
 */
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class FeignOrderMain8080 {
    public static void main(String[] args) {
        SpringApplication.run(FeignOrderMain8080.class, args);
    }
}
