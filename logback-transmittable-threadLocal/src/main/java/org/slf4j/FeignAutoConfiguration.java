package org.slf4j;

import feign.RequestInterceptor;
import org.slf4j.interceptors.MDCRequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 当RequestInterceptor类存在时才会加载到IOC容器
 *
 * @author: 苦瓜不苦
 * @date: 2022/6/30 23:33
 **/
@Configuration
@ConditionalOnClass(RequestInterceptor.class)
public class FeignAutoConfiguration {


    /**
     * feign拦截器
     *
     * @return
     */
    @Bean
    public MDCRequestInterceptor mdcRequestInterceptor() {
        return new MDCRequestInterceptor();
    }

}
