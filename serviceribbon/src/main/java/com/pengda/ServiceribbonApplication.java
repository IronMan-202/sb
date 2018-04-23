package com.pengda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient		//通过EnableDiscoveryClient向服务中心注册
public class ServiceribbonApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceribbonApplication.class, args);
	}

	/*像程序的IOC中注入一个bean:RestTemplate*/
	/*并且通过@LoadBalanced注解表明这个RestTemplate开启负载均衡功能*/
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
