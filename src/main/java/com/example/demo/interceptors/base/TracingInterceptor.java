package com.example.demo.interceptors.base;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author fanhuijie
 * @date 2021/2/23
 * @desc
 */
@Slf4j
public class TracingInterceptor implements HandlerInterceptor {

    public static final String USER_ID = "userId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        log.info("set userId");
        MDC.put(USER_ID,request.getSession().getId());

//        log.info("preHandle");

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        log.info("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        log.info("afterCompletion");

        try {
            MDC.remove("userId");
//            log.info("remove userId");
        } catch (Exception e) {
            log.error("user trace error",e);
        }
    }
}
