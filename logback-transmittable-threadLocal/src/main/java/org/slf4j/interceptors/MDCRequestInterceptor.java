package org.slf4j.interceptors;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.utils.MdcUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Objects;

/**
 * feign拦截器
 *
 * @author: 苦瓜不苦
 * @date: 2022/6/30 22:30
 **/
public class MDCRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.isNull(attributes)) {
            return;
        }

        HttpServletRequest request = attributes.getRequest();
        // cookie
        Cookie[] cookies = request.getCookies();
        if (Objects.nonNull(cookies)) {
            for (Cookie cookie : cookies) {
                requestTemplate.header(cookie.getName(), cookie.getValue());
            }
        }
        // header
        Enumeration<String> headerNames = request.getHeaderNames();
        if (Objects.nonNull(headerNames)) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String value = request.getHeader(name);
                // 便利请求头属性,转发到下游服务
                requestTemplate.header(name, value);
            }
        }

        // 链路追踪mdc
        requestTemplate.header(MdcUtil.TRACE_ID, MdcUtil.get());
    }


}
