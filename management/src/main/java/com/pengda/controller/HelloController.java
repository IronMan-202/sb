package com.pengda.controller;

import com.pengda.config.RequestInfo;
import com.pengda.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/10/31.
 */
@RequestMapping(path = "/pengda/manager")
@RestController
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private UserService userService ;


    //@RequestMapping(value = "/hello",method = RequestMethod.POST)
    //@PostMapping(value = "/hello")
    //http://localhost:8080/hello
    /*@RequestMapping(value = "/hello")
    public String say(){
        return "Hello SpringBoot" ;
    }*/

    @RequestMapping(value = "/sing/{id}",method = RequestMethod.GET)
    public String sing(@PathVariable int id){
        return "Hello" + id ;
    }
    @RequestMapping(value = "/song/{id}",method = RequestMethod.POST)
    public String song(@PathVariable int id){
        return "song"+id ;
    }

    @RequestMapping(value = "/getUserName/{id}",method = RequestMethod.POST)
    public String getUserName(@RequestBody RequestInfo<Integer> id){
        LOGGER.info("通过ID获取姓名");
        return this.userService.getUserName(id.getQuery()).getLoginName();
    }
}
