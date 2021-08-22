package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 引入Hystrix
 * @author chendm
 * @create 2021-08-22 13:37
 */
@Component
@FeignClient(value = "cloud-provider-hystrix-payment")
public interface HystrixOrderService {

    @GetMapping("/hystrix/payment/ok/{id}")
    public String payment_ok(@PathVariable("id") String id);

    @GetMapping("/hystrix/payment/timeout/{id}")
    public String payment_timeout(@PathVariable("id") String id);
}
