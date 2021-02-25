package com.example.demo.interceptors.base;

import com.example.demo.interceptors.TimingInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author fanhuijie
 * @date 2021/2/24
 * @desc
 */
@Slf4j
public class TimingForController extends TimingInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        startNewTask(TASK_CONTROLLER);
        return true;
    }



    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        startNewTask(TASK_INTERCEPTOR_TIMING_POST_HANDLE);

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        startNewTask(TASK_INTERCEPTOR_TIMING_AFTER_COMPLETION);
    }
}
