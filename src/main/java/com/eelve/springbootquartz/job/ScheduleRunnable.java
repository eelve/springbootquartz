package com.eelve.springbootquartz.job;


import com.eelve.springbootquartz.exception.EIPException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * 执行定时任务
 */
@Slf4j
public class ScheduleRunnable implements Runnable {
    private Object target;
    private Method method;
    private String params;

    private Object getJob(String className) {
        try {
            Class<?> jobClass = Class.forName(className);
            return jobClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ScheduleRunnable(String className, String methodName, String params) throws NoSuchMethodException, SecurityException {
        //this.target = SpringContext.getBean(beanName);
        this.target = getJob(className);
        this.params = params;

        if (StringUtils.isNotBlank(params)) {
            this.method = target.getClass().getDeclaredMethod(methodName, String.class);
        } else {
            this.method = target.getClass().getDeclaredMethod(methodName);
        }
    }

    @Override
    public void run() {
        try {
            ReflectionUtils.makeAccessible(method);
            if (StringUtils.isNotBlank(params)) {
                method.invoke(target, params);
            } else {
                method.invoke(target);
            }
        } catch (Exception e) {
            log.error("执行定时任务失败", e);
            throw new EIPException("执行定时任务失败", e);
        }
    }

}
