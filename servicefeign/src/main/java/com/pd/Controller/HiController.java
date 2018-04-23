package com.pd.Controller;

import com.pd.feign.ServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/4/23.
 */
@RestController
public class HiController {

    @Autowired
    ServiceFeignClient serviceFeignClient;
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public String sayHi(@RequestParam String name){
        return serviceFeignClient.sayHiFromClientOne(name);
    }
}
