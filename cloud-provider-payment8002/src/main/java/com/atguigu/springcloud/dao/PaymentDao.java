package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author chendm
 * @create 2021-08-01 10:45
 * 建议使用@Mapper注解，不建议使用@Repository,因为有时候，这个注解会注入不进去
 */
@Mapper

public interface PaymentDao {

    //写
    public int create(Payment payment);

    //读
    public Payment getPaymentById(@Param("id") Long id);
}
