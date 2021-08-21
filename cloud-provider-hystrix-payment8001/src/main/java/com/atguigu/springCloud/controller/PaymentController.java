package com.atguigu.springCloud.controller;

import com.atguigu.springCloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author chendm
 * @create 2021-08-21 18:27
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/hystrix/payment/ok/{id}")
    public String payment_ok(@PathVariable("id") String id){
        String result = paymentService.paymentService_ok(id);
        log.info("------result:"+result);
        return result;
    }

    @GetMapping("/hystrix/payment/timeout/{id}")
    public String payment_timeout(@PathVariable("id") String id){
        String result = paymentService.paymentService_timeout(id);
        log.info("---------result:"+result);
        return result;
    }
}
