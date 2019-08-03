package com.eelve.springbootquartz.dao;

import com.eelve.springbootquartz.entity.ScheduleJobLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhao.zhilue
 * @Description:
 * @date 2019/8/215:40
 */
@Mapper
public interface ScheduleJobLogDao {
    void saveLog(ScheduleJobLogEntity scheduleJobLogEntity);
}
