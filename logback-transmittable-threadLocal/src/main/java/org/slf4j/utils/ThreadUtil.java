package org.slf4j.utils;

import org.slf4j.configure.ThreadPoolTaskExecutorConfigure;
import org.slf4j.properties.ThreadProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池工具类
 *
 * @author: 苦瓜不苦
 * @date: 2022/7/1 20:57
 **/
@EnableConfigurationProperties(ThreadProperties.class)
public class ThreadUtil {

    private static ThreadPoolTaskExecutor threadPoolTaskExecutor;


    /**
     * 初始化线程池
     *
     * @param threadProperties 线程配置对象
     */
    public ThreadUtil(ThreadProperties threadProperties) {
        threadPoolTaskExecutor = new ThreadPoolTaskExecutorConfigure();
        // 设置核心线程数
        threadPoolTaskExecutor.setCorePoolSize(threadProperties.getCorePoolSize());
        // 设置最大线程数
        threadPoolTaskExecutor.setMaxPoolSize(threadProperties.getMaxPoolSize());
        // 配置队列大小
        threadPoolTaskExecutor.setQueueCapacity(threadProperties.getQueueCapacity());
        // 设置线程活跃时间(秒)
        threadPoolTaskExecutor.setKeepAliveSeconds(threadProperties.getKeepAliveSeconds());
        // 设置默认线程池名称
        threadPoolTaskExecutor.setThreadNamePrefix(threadProperties.getThreadNamePrefix());
        // 等待所有任务结束后再关闭线程池
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(threadProperties.getWaitForJobsToCompleteOnShutdown());
        // 执行初始化
        threadPoolTaskExecutor.initialize();
    }


    /**
     * 挂起当前线程
     *
     * @param millis 挂起的毫秒数
     * @return 被中断返回false，否则true
     */
    public static boolean sleep(long millis) {
        if (millis > 0) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取线程池
     *
     * @return 线程池
     */
    public static ThreadPoolExecutor getThreadPoolExecutor() {
        return threadPoolTaskExecutor.getThreadPoolExecutor();
    }


    /**
     * 启用线程
     *
     * @param runnable 需要执行的方法体
     */
    public static void execute(Runnable runnable) {
        threadPoolTaskExecutor.execute(runnable);
    }

    /**
     * 启用线程
     *
     * @param runnable     需要执行的方法体
     * @param startTimeout 开始时间(单位:毫秒)
     */
    public static void execute(Runnable runnable, long startTimeout) {
        threadPoolTaskExecutor.execute(runnable, startTimeout);
    }

    /**
     * 执行有返回值的异步方法
     *
     * @param runnable 需要执行的方法体
     * @return
     */
    public static Future<?> submit(Runnable runnable) {
        return threadPoolTaskExecutor.submit(runnable);
    }

    /**
     * 执行有返回值的异步方法
     *
     * @param callable 需要执行的方法体
     * @param <T>      回调类型 T
     * @return
     */
    public static <T> Future<T> submit(Callable<T> callable) {
        return threadPoolTaskExecutor.submit(callable);
    }


}
