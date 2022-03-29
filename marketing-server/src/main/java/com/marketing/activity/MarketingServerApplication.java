package com.marketing.activity;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 营销活动启动项
 * @author yyz
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class MarketingServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketingServerApplication.class, args);
    }
}
