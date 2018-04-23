package com.pd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients			//EnableFeifnClients即开启Feign功能
public class ServicefeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicefeignApplication.class, args);
	}
}
