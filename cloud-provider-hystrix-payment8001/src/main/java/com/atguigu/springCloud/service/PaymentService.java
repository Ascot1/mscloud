package com.atguigu.springCloud.service;

/**
 * @author chendm
 * @create 2021-08-21 18:16
 */
public interface PaymentService {

    /**
     * 正常的程序
     * @param id
     * @return
     */
    public String paymentService_ok(String id);

    /**
     * 可能会出现异常的程序
     * @param id
     * @return
     */
    public String paymentService_timeout(String id);
}
