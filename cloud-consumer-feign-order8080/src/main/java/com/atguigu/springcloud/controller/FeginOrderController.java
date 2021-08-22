package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.FeignOrderService;
import com.atguigu.springcloud.service.HystrixOrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 客户端使用hystrix
 * @author chendm
 * @create 2021-08-15 23:42
 */
@RestController
@EnableDiscoveryClient
public class FeginOrderController {

    @Resource
    private FeignOrderService feignOrderService;

    @Resource
    private HystrixOrderService hystrixOrderService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return feignOrderService.getPaymentById(id);
    }

    @GetMapping("/consumer/hystrix/payment/ok/{id}")
    public String payment_ok(@PathVariable("id") String id){
        String result = hystrixOrderService.payment_ok(id);
        return result;
    }

    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value =  "1500")}) //3秒钟以内就是正常的业务逻辑)
    @GetMapping("/consumer/hystrix/payment/timeout/{id}")
    public String payment_timeout(@PathVariable("id") String id){
        String result = hystrixOrderService.payment_timeout(id);
        return result;
    }

    //兜底方法
    public String paymentTimeOutFallbackMethod(@PathVariable("id") String id){
        return "我是消费者80，对付支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,(┬＿┬)";
    }
}
