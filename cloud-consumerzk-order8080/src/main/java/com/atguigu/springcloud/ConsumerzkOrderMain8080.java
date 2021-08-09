package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * zookeeper 服务消费者
 * @author chendm
 * @create 2021-08-09 21:37
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerzkOrderMain8080 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerzkOrderMain8080.class, args);
    }
}
