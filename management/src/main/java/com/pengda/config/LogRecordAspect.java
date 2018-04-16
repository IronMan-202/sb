package com.pengda.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by pengda on 17/12/07.
 */
//描述切面类
@Aspect     //@Aspect注解就是告诉spring 这是一个aop类,AOP切面
@Configuration
public class LogRecordAspect {
private static final Logger logger = LoggerFactory.getLogger(LogRecordAspect.class);

    // 用@Pointcut来注解一个切入方法
    //@Pointcut注解 声明这是一个需要拦截的切面，也就是说，当调用任何一个controller方法的时候，都会激活这个aop
    @Pointcut("execution(* com.pengda.controller.*Controller.*(..))")   //两个..代表所有子目录，最后括号里的两个..代表所有参数
    public void excudeService() {
    }

    //@Around注解 环绕执行，就是在调用之前和调用之后，都会执行一定的逻辑
    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();


        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        try{
            Object[] args =pjp.getArgs();
            for(int i=0;i<args.length;i++){
                if(args[i] instanceof RequestInfo<?>){
                    RequestInfo<?> r= (RequestInfo<?>) args[i];
                    logger.info("请求开始, 各个参数, url: {}, method: {}, uri: {}, params: {}", url, method, uri, r.toString());
                }else{
                    logger.info("请求开始, 各个参数, url: {}, method: {}, uri: {}, params: {}", url, method, uri,args[i]);
                }
            }
        }catch (Exception e){
            logger.info("请求开始, 各个参数, url: {}, method: {}, uri: {}, params: {}", url, method, uri,queryString);
        }
        // result的值就是被拦截方法的返回值
        Object result = pjp.proceed();
        //logger.info("请求结束，controller的返回值是 " + result.toString());
        //logger.info("请求结束，controller的返回值是 " + result);
        return result;
    }
}