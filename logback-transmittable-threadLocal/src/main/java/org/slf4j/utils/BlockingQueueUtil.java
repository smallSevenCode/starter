package org.slf4j.utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 阻塞队列工具类
 *
 * @author: 苦瓜不苦
 * @date: 2022/6/30 23:46
 **/
public class BlockingQueueUtil {


    // 队列大小
    private final static int QUEUE_MAX_SIZE = 20000;
    // 阻塞队列
    private final static BlockingQueue<String> BLOCKINGQUEUE = new LinkedBlockingQueue<>(QUEUE_MAX_SIZE);

    /**
     * 消息入队
     *
     * @param message 消息体
     * @return
     */
    public static void push(String message) {
        // 队列满了就抛出异常，不阻塞
        BLOCKINGQUEUE.add(message);
    }

    /**
     * 消息出队
     *
     * @return
     */
    public static String poll() {
        try {
            return BLOCKINGQUEUE.take();
        } catch (InterruptedException e) {
            return null;
        }
    }


}
