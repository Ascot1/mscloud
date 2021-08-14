package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author chendm
 * @create 2021-08-14 11:42
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerOrderMain8080 {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrderMain8080.class, args);
    }
}
