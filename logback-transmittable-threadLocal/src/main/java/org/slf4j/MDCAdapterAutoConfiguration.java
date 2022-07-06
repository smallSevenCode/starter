package org.slf4j;

import org.slf4j.configure.ThreadPoolTaskExecutorConfigure;
import org.slf4j.properties.ThreadProperties;
import org.slf4j.spi.MDCAdapter;
import org.slf4j.utils.ThreadUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 自动装配
 *
 * @author: 苦瓜不苦
 * @date: 2022/6/29 20:52
 **/
@Configuration
public class MDCAdapterAutoConfiguration {

    /**
     * MDC日志
     *
     * @return
     */
    @Bean
    public MDCAdapter mdcAdapter() {
        return TransmittableMDCAdapter.getInstance();
    }

    /**
     * 线程池
     *
     * @return
     */
    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        return new ThreadPoolTaskExecutorConfigure();
    }

    /**
     * 线程配置类
     *
     * @return
     */
    @Bean
    public ThreadProperties threadProperties() {
        return new ThreadProperties();
    }

    /**
     * 线程工具类
     *
     * @return
     */
    @Bean
    public ThreadUtil threadUtil() {
        return new ThreadUtil(threadProperties());
    }


}
