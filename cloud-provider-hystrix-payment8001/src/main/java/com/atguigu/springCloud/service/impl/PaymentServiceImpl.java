package com.atguigu.springCloud.service.impl;

import com.atguigu.springCloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author chendm
 * @create 2021-08-21 18:18
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String paymentService_ok(String id) {
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_OK,id：  "+id+"\t"+"哈哈哈"  ;
    }

    /**
     * 添加Hystrix处理服务降级的注解，当该方法出现异常或者超时3秒之后，就会去进行服务降级，执行paymentInfo_TimeOutHandler方法
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value="3000")
    })
    @Override
    public String paymentService_timeout(String id) {
        int timeNum = 3; //超时时间
        //int age = 10/0;
        try { TimeUnit.SECONDS.sleep(timeNum); }catch (Exception e) {e.printStackTrace();}
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_TimeOut,id：  "+
                id+"\t"+"呜呜呜"+" 耗时(秒)"+timeNum;
    }

    @Override
    public String paymentInfo_TimeOutHandler(String id) {
        return "线程池："+Thread.currentThread().getName()+"系统繁忙，请稍后再试";
    }


}
