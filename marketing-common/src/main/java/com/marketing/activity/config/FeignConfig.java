package com.marketing.activity.config;

import com.marketing.activity.constant.RequestConstants;
import feign.Client;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.codec.Encoder;
import feign.form.FormEncoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.openfeign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.UUID;

@Slf4j
@Configuration
public class FeignConfig implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String traceId = "";
        String token = "";
        // 1 request中取token
        if (null != attributes) {
            HttpServletRequest request = attributes.getRequest();
            traceId = request.getHeader(RequestConstants.KEY_TRACE_ID);
            token = request.getHeader(RequestConstants.KEY_TOKEN);
        }

        if (StringUtils.isBlank(traceId)) {
            // 处理默认值
            traceId = MDC.get(RequestConstants.KEY_TRACE_ID) == null ? UUID.randomUUID().toString() : MDC.get(RequestConstants.KEY_TRACE_ID);
        }

        requestTemplate.header(RequestConstants.KEY_TOKEN, StringUtils.isBlank(token) ? "uoc_default_token" : token);
        requestTemplate.header(RequestConstants.KEY_AUTHORIZATION, StringUtils.isBlank(token) ? "uoc_default_token" : token);
        requestTemplate.header(RequestConstants.KEY_TRACE_ID, traceId);

        // log.info("feign interceptor requestTemplate:{}", requestTemplate);
    }

    @Bean
    @ConditionalOnMissingBean
    public Client feignClient(CachingSpringLoadBalancerFactory cachingFactory,SpringClientFactory clientFactory) throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext ctx = SSLContext.getInstance("SSL");
        X509TrustManager tm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        ctx.init(null, new TrustManager[]{tm}, null);
        return new LoadBalancerFeignClient(new Client.Default(ctx.getSocketFactory(), (hostname, session) -> true), cachingFactory, clientFactory);
    }

    // 这里会由容器自动注入HttpMessageConverters的对象工厂
    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    // new一个form编码器，实现支持form表单提交
    // 注意这里方法名称，也就是bean的名称是什么不重要，
    // 重要的是返回类型要是 Encoder 并且实现类必须是 FormEncoder 或者其子类
    @Bean
    public Encoder feignFormEncoder() {
        return new FormEncoder(new SpringEncoder(messageConverters));
    }
}
