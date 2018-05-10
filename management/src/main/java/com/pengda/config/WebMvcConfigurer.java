package com.pengda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
//Spring Boot提供了基础类WebMvcConfigurerAdapter,我们项目中的WebMvcConfigurer类需要继承这个类
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    //  通过此方法，添加拦截器，可以是spring提供的，也可以是自己添加的
    //典型的回调函数——利用该函数的参数registry来添加自定义的拦截器
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestLog()).addPathPatterns("/pengda/**");
        //全部拦截
        //registry.addInterceptor(new RequestLog()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
    /*@Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        MyFilter httpBasicFilter = new MyFilter();
        registrationBean.setFilter(httpBasicFilter);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("*//*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }*/
    /**
     * 解决前端跨域请求
     * <p>
     * 简单跨域就是GET，HEAD和POST请求，但是POST请求的"Content-Type"只能是application/x-www-form-urlencoded, multipart/form-data 或 text/plain
     * <p>
     * 反之，就是非简单跨域，此跨域有一个预检机制，说直白点，就是会发两次请求，一次OPTIONS请求，一次真正的请求
     *
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 允许cookies跨域
        corsConfiguration.setAllowCredentials(true);
        // #允许向该服务器提交请求的URI，*表示全部允许，在SpringMVC中，如果设成*，会自动转成当前请求头中的Origin
        corsConfiguration.addAllowedOrigin("*");
        // #允许访问的头信息,*表示全部
        corsConfiguration.addAllowedHeader("*");
        // 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
        corsConfiguration.setMaxAge(18000L);
        //允许所有的请求方式
        corsConfiguration.addAllowedMethod("*");
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}