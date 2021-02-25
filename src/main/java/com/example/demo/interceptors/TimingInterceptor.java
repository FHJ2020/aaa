package com.example.demo.interceptors;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

/**
 * @author fanhuijie
 * @date 2021/2/25
 * @desc
 */
@Slf4j
public abstract class TimingInterceptor implements HandlerInterceptor {
    protected String TASK_INTERCEPTOR_TIMING_PRE_HANDLE = "TASK_INTERCEPTOR_TIMING_PRE_HANDLE";
    protected String TASK_INTERCEPTOR_TIMING_POST_HANDLE = "TASK_INTERCEPTOR_TIMING_POST_HANDLE";
    protected String TASK_INTERCEPTOR_TIMING_AFTER_COMPLETION = "TASK_INTERCEPTOR_TIMING_AFTER_COMPLETION";
    protected String TASK_CONTROLLER = "TASK_CONTROLLER";

    protected List<String> TASK_INTERCEPTORS = Lists.newArrayList(TASK_INTERCEPTOR_TIMING_PRE_HANDLE, TASK_INTERCEPTOR_TIMING_POST_HANDLE, TASK_INTERCEPTOR_TIMING_AFTER_COMPLETION);


    protected ThreadLocal<StopWatch> STOP_WATCH_THREAD_LOCAL = new ThreadLocal<>();

    protected void startNewTask(String taskName) {
        StopWatch stopWatch = STOP_WATCH_THREAD_LOCAL.get();
        if (stopWatch == null) {
            log.error("stopWatch is null");
            return;
        }
        if (stopWatch.isRunning()){
            stopWatch.stop();
        }
        stopWatch.start(taskName);
    }
}
