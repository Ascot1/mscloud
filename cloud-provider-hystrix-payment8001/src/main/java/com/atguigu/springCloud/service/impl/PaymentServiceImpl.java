package com.atguigu.springCloud.service.impl;

import com.atguigu.springCloud.service.PaymentService;
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

    @Override
    public String paymentService_timeout(String id) {
        int timeNum = 3; //超时时间
        try { TimeUnit.SECONDS.sleep(timeNum); }catch (Exception e) {e.printStackTrace();}
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_TimeOut,id：  "+
                id+"\t"+"呜呜呜"+" 耗时(秒)"+timeNum;
    }
}
