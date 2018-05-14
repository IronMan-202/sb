package com.pengda.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.time.Instant;

/*
    HandlerInterceptoer拦截的是请求地址,所以针对请求地址做一些验证、预处理等操作比较合适，
    比如需要统计请求这个地址的响应时间
*/
/*  Filter是Servlet规范规定的，不属于spring框架，也是用于请求的拦截。
    但是它适合更粗粒度的拦截，在请求前后做一些编解码处理、日志记录等。
*/

public class RequestLog extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestLog.class);

    /**
     * 前置检查,方法执行前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String ip = request.getRemoteAddr();
        Instant startTime = Instant.now();
        request.setAttribute("logrequestStartTime", startTime);
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 获取用户token
        Method method = handlerMethod.getMethod();
        LOGGER.info("用户:"+ip+",访问目标:"+method.getDeclaringClass().getName() + "." + method.getName());
        return true;
    }

    /**
     *  方法执行中
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    // controller处理完成
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Instant startTime = (Instant) request.getAttribute("logrequestStartTime");

        Instant endTime = Instant.now();
        long executeTime = endTime.toEpochMilli()- startTime.toEpochMilli();

        // log it
        if (executeTime > 1000) {
            LOGGER.info("[" + method.getDeclaringClass().getName() + "." + method.getName() + "] 执行耗时 : "
                    + executeTime + "ms");
        } else {
            LOGGER.info("[" + method.getDeclaringClass().getSimpleName() + "." + method.getName() + "] 执行耗时 : "
                    + executeTime + "ms");
        }

    }
}