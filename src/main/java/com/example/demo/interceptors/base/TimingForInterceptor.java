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
 * @descn
 */
@Slf4j
public class TimingForInterceptor extends TimingInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(TASK_INTERCEPTOR_TIMING_PRE_HANDLE);
        STOP_WATCH_THREAD_LOCAL.set(stopWatch);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        StopWatch stopWatch = STOP_WATCH_THREAD_LOCAL.get();
        if (stopWatch==null || !stopWatch.isRunning()){
            return;
        }
        stopWatch.stop();

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        StopWatch stopWatch = STOP_WATCH_THREAD_LOCAL.get();
        if (stopWatch==null || !stopWatch.isRunning()){
            return;
        }
        stopWatch.stop();

        Long interceptorsExecuteCost = 0L;
        Long controllerExecuteCost = 0L;
        for (StopWatch.TaskInfo taskInfo : stopWatch.getTaskInfo()) {
            if (TASK_INTERCEPTORS.contains(taskInfo.getTaskName())) {
                interceptorsExecuteCost += taskInfo.getTimeMillis();
                continue;
            }
            if (TASK_CONTROLLER.equals(taskInfo.getTaskName())) {
                controllerExecuteCost = taskInfo.getTimeMillis();
                continue;
            }
        }
        request.setAttribute("interceptorsExecuteCost", interceptorsExecuteCost);
        request.setAttribute("controllerExecuteCost", controllerExecuteCost);

        STOP_WATCH_THREAD_LOCAL.remove();
    }
}
