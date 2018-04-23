package com.pengda.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2018/4/21.
 */
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    /*在方法上加上@HystrixCommand注解，该注解为该方法创建了熔断器功能，
    并制定fallbackMethod熔断方法，此方法返回一个字符串*/
    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name) {
        return restTemplate.getForObject("http://SERVICE-HI/hi?name="+name,String.class);
    }

    public String hiError(String name) {
        return "hi"+name+"sorry,error!";
    }

    /*当SERVICE-HI服务关闭时，SERVICERIBBON服务调用SERVICE-HI服务，
    会执行快速失败，直接返回字符串，而不是等待响应超时，很好的控制容器的线程阻塞*/
}
