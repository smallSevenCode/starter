package org.slf4j.interceptors;

import org.slf4j.utils.MdcUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 链路追踪拦截器
 *
 * @author: 苦瓜不苦
 * @date: 2022/6/30 21:14
 **/
public class MDCHandlerInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求头中的链路追踪唯一ID,避免远程调用
        MdcUtil.set(request.getHeader(MdcUtil.TRACE_ID));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清除,避免内存泄露
        MdcUtil.clear();
    }

}
