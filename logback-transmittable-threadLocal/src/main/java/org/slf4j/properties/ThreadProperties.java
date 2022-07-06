package org.slf4j.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: 苦瓜不苦
 * @date: 2022/7/1 21:05
 **/
@Data
@ConfigurationProperties(prefix = "thread")
public class ThreadProperties {

    /**
     * 核心线程数
     */
    private int corePoolSize = 5;

    /**
     * 最大线程数
     */
    private int maxPoolSize = 20;

    /**
     * 队列大小
     */
    private int queueCapacity = Integer.MAX_VALUE;

    /**
     * 线程活跃时间(秒)
     */
    private int keepAliveSeconds = 60;

    /**
     * 是否所有任务结束之后再关闭线程
     */
    private Boolean waitForJobsToCompleteOnShutdown = true;

    /**
     * 默认线程池名称
     */
    private String threadNamePrefix;


}
