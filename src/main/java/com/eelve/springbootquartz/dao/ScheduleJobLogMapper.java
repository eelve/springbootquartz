package com.eelve.springbootquartz.dao;


import com.eelve.springbootquartz.entity.ScheduleJobLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 定时任务日志
 */
@Mapper
public interface ScheduleJobLogMapper extends AdminBaseMapper<ScheduleJobLogEntity> {

    ScheduleJobLogEntity testSelect(@Param("logId") Long logId);
}
