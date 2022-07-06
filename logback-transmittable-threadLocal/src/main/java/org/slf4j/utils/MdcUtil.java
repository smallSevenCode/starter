package org.slf4j.utils;

import org.slf4j.MDC;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * 链路追踪工具类
 *
 * @author: 苦瓜不苦
 * @date: 2022/6/30 20:55
 **/
public class MdcUtil {

    /**
     * 链路追踪key值
     */
    public final static String TRACE_ID = "traceId";

    private MdcUtil() {

    }

    /**
     * 设置全局唯一ID
     *
     * @param value ID值
     */
    public static void set(String value) {
        MDC.put(TRACE_ID, Objects.nonNull(value) ? value : UUID.randomUUID().toString().replaceAll("-", ""));
    }


    /**
     * 获取ID值
     *
     * @return
     */
    public static String get() {
        return MDC.get(TRACE_ID);
    }


    /**
     * 获取MDC上下文对象
     *
     * @return
     */
    public static Map<String, String> getMap() {
        return MDC.getCopyOfContextMap();
    }

    /**
     * 设置MDC上下文对象
     *
     * @param map 上下文对象
     */
    public static void setMap(Map<String, String> map) {
        if (Objects.nonNull(map)) {
            MDC.setContextMap(map);
        }
    }


    /**
     * 清空MDC
     */
    public static void clear() {
        MDC.clear();
    }


}
