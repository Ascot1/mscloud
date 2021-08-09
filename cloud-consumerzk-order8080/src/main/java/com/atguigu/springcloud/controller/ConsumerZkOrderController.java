package com.atguigu.springcloud.controller;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * zookeeper 消费者controller
 * @author chendm
 * @create 2021-08-09 21:42
 */
@RestController
@EnableDiscoveryClient
public class ConsumerZkOrderController {

    private final static String INVOME_URL = "http://cloud-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumerZk/payment")
    public String payment(){
        String s = restTemplate.getForObject(INVOME_URL + "/payment/zk", String.class);
        return s;
    }
}
