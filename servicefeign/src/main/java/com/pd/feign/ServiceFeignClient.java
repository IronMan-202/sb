package com.pd.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2018/4/23.
 */

/**
 * 定义一个Feign接口，通过@FeignClient指定调用哪个服务，例如，在service-hi服务中调用"hi接口"
* */
@Service
@FeignClient(value = "service-hi")
public interface  ServiceFeignClient {

    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
}
