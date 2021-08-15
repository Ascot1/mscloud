package com.atguigu.springcloud.lb.impl;

import com.atguigu.springcloud.lb.LoadBalance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.List;

/**
 * 负载均衡轮询算法（模仿源码编写）
 * @author chendm
 * @create 2021-08-15 18:04
 */
@Component
public class MyLB implements LoadBalance {

    //保证原子性（用于高并发）
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private final int getAndIncrement(){
        int current;
        int next;
        do{
            current = this.atomicInteger.get();
            //2147483647为int的最大数值
            next = current >= 2147483647 ? 0 : current + 1;
            //对compareAndSet(current, next)方法的解释：如果current期待值与硬件上记录的原子值相等，才会去更新atomicInteger为next的值
        }while(!this.atomicInteger.compareAndSet(current, next));
        System.out.println("第"+next+"次请求");
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        //取余获取服务器集群中的服务器下标
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
