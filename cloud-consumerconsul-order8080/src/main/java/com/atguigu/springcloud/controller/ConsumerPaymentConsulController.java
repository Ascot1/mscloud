package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author chendm
 * @create 2021-08-14 11:44
 */
@RestController
public class ConsumerPaymentConsulController {

    private final static String PAYMENT_URL = "http://consul-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/consul")
    public String paymentConsul(){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/consul", String.class);
    }
}
