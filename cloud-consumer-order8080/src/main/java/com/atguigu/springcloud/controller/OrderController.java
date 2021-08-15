package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.LoadBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * 消费端80
 * @author chendm
 * @create 2021-08-01 16:07
 */
@RestController
@Slf4j
public class OrderController {

    //private final static String PAYMENT_URL = "http://localhost:8001";
    //以后使用微服务，请求的url不能写死，请使用http://微服务名称，来进行访问（这里主要是用到了rabbion的负载均衡知识）
    private final static String PAYMENT_URL = "http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalance loadBalance; //注入手写的负载均衡接口（轮询方式）

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable String id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id, CommonResult.class);
    }

    /**
     * 测试手写的轮询算法
     * @return
     */
    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLB(){
        // 获取cloud-payment-service服务下面的所有实例
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        if(instances == null || instances.size() < 1){
            return null;
        }
        ServiceInstance service = loadBalance.instances(instances);
        //获取轮询之后，拿到的服务实例对应的url
        URI uri = service.getUri();
        //请求服务实例对应的方法
        return restTemplate.getForObject(uri + "/payment/lb", String.class);
    }
}
