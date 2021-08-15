package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Spring提供的封装了httpClient模板类RestTemplate注入到Spring容器中（这个配置只是前期零基础阶段需要用到）
 * @LoadBalanced: 开启restTemplate的负载均衡配置
 * @author chendm
 * @create 2021-08-01 16:12
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    //@LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
