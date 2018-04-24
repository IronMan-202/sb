package com.pd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy		//使用@EnableZuulProxy注解，开启Zuul功能
public class ServicezuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicezuulApplication.class, args);
	}
}

/*
	Zuul连上物理机后，如果使用默认配置，经常会出现超时，如 "read time out","forwarding error"
	在application.yml配置
	zuul:
    	host:
			connect-timeout-millis: 10000
			socket-timeout-millis: 60000

	hystrix:
		command:
			default:
				execution:
					isolation:
						thread:
							timeoutInMilliseconds: 60000
	*/
