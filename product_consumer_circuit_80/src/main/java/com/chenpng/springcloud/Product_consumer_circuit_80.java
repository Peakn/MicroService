package com.chenpng.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Base64;

/**
 * @author chenpeng
 */
@SpringBootApplication
@EnableCircuitBreaker
@EnableFeignClients
public class Product_consumer_circuit_80 {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public HttpHeaders getHeaders() { // 要进行一个Http头信息配置
        HttpHeaders headers = new HttpHeaders();
        // 定义一个HTTP的头信息
        String auth = "admin:123456";
        // 认证的原始信息
        byte[] encodedAuth = Base64.getEncoder()
                .encode(auth.getBytes(Charset.forName("US-ASCII")));
        // 进行一个加密的处理
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);
        return headers;
    }

    public static void main(String[] args) {
        SpringApplication.run(Product_consumer_circuit_80.class, args);
    }
}
