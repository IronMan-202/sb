package com.pengda.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;


/**
 * 使用注解标注过滤器
 *
 * @author 单红宇(365384722)
 * @WebFilter将一个实现了javax.servlet.Filter接口的类定义为过滤器 属性filterName声明过滤器的名称, 可选
 * 属性urlPatterns指定要过滤 的URL模式,也可使用属性value来声明.(指定要过滤的URL模式是必选属性)
 * @myblog http://blog.csdn.net/catoop/
 * @create 2016年1月6日
 */
@WebFilter(filterName = "myFilter", urlPatterns = "/*")
public class MyFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogRecordAspect.class);

    /**
     * 过滤器初始化
     *
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig config) throws ServletException {
        LOGGER.info("过滤器初始化");
    }

    /**
     * 执行过滤器请求
     *
     * @param request  req
     * @param response res
     * @param chain    do
     * @throws IOException      io
     * @throws ServletException servlet
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        // 防止流读取一次后就没有了, 所以需要将流继续写出去
        ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(httpServletRequest);
        String body = HttpHelper.getBodyString(requestWrapper);
        //如果请求Body为空异常处理
        if (StringUtils.isBlank(body)) {
            chain.doFilter(request, response);
            return;
        }
        //开始处理请求体
        try {
            LOGGER.info("request：" + body);
            Map<String, Object> parameters = new ObjectMapper().readValue(body.replaceAll("&quot;", "\""), new TypeReference<Map<String, Object>>() {
            });
            Object imReqeustEntityo = parameters.get("imReqeustEntity");
            if (imReqeustEntityo == null || "".equals(imReqeustEntityo)) {
//                LOGGER.error("非法请求, 没有设备信息");
            } else {
                Map<String, String> imReqeustEntity = (Map<String, String>) imReqeustEntityo;
                if (imReqeustEntity == null || "".equals(imReqeustEntity)) {
//                    LOGGER.error("非法请求, 没有设备信息");
                }
                LOGGER.info("设备信息为: " + imReqeustEntity.toString());
            }
            chain.doFilter(requestWrapper, response);
//            // 使用我们自定义的响应包装器来包装原始的ServletResponse
//            ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse) response);
//            // 这句话非常重要，注意看到第二个参数是我们的包装器而不是response
//            chain.doFilter(requestWrapper, responseWrapper);
//            byte[] data = responseWrapper.getResponseData();
//            String result = new String(data, "utf-8");
//            LOGGER.info("返回明文：" + result);
//            AES2 aes2 = new AES2();
//            result = aes2.encrypt(result.getBytes("utf-8"));
//            LOGGER.info("返回密文：" + result);
//            // 输出最终的结果
//            ServletOutputStream out = response.getOutputStream();
//            out.write(result.getBytes());
//            out.flush();
//            out.close();
        } catch (Exception e) {
            chain.doFilter(requestWrapper, response);
        }
    }

    @Override
    public void destroy() {
        LOGGER.info("过滤器销毁");
    }

}