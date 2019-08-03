package com.eelve.springbootquartz.service.impl;

import com.eelve.springbootquartz.dao.ScheduleJobLogDao;
import com.eelve.springbootquartz.entity.ScheduleJobLogEntity;
import com.eelve.springbootquartz.service.ScheduleJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName ScheduleJobLogServiceImpl
 * @Description TDO
 * @Author zhao.zhilue
 * @Date 2019/8/2 15:36
 * @Version 1.0
 **/
@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl implements ScheduleJobLogService {

    @Autowired
    ScheduleJobLogDao scheduleJobLogDao;

    @Override
    public void saveLog(ScheduleJobLogEntity scheduleJobLogEntity) {
        scheduleJobLogDao.saveLog(scheduleJobLogEntity);
    }
}
