package com.eelve.springbootquartz.service;

import com.eelve.springbootquartz.entity.ScheduleJobLogEntity;
import com.eelve.springbootquartz.entity.TaskEntity;

import java.util.List;

/**
 * @author zhao.zhilue
 * @Description: ScheduleJobLogService
 * @date 2019/8/117:24
 */
public interface ScheduleJobLogService {

    void saveLog(ScheduleJobLogEntity scheduleJobLogEntity);
}
