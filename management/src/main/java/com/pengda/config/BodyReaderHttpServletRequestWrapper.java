package com.pengda.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * 使用HttpServletRequestWrapper来包装HttpServletRequest
 * 在BodyReaderHttpServletRequestWrapper中初始化读取request的InputStream数据，以byte[]形式缓存在其中，然后在Filter中将request转换为包装过的request
 */
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {

    /**
     * 日志打印
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BodyReaderHttpServletRequestWrapper.class);

    /**
     * 原加密请求体
     */
    private final byte[] body;

    /**
     * 后解密请求体
     */
    private final byte[] resetBody;

    /**
     * 处理request中的请求
     *
     * @param request
     * @throws IOException
     */
    public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        String rBody = HttpHelper.getBodyString(request);
        body = rBody.getBytes(Charset.forName("UTF-8"));
        //判断请求体是否为空
//        if (!CommonUtil.isEmpty(rBody)) {
//            LOGGER.info("开始进行AES解密处理...");
//            String aesBody = new String(body, "utf-8");
//            LOGGER.info("请求加密报文：" + aesBody);
//            AES2 aes = new AES2();
//            aesBody = aes.decrypt(aesBody);
//            resetBody = aesBody.getBytes();
//        } else {
//            resetBody = body;
//        }
        resetBody = body;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    /**
     * 重置request中的inputStream
     *
     * @return i
     * @throws IOException ioe
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {

        final ByteArrayInputStream bais = new ByteArrayInputStream(resetBody);

        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }
        };
    }
}