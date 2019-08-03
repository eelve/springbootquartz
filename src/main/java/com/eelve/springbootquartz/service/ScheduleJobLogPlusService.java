package com.eelve.springbootquartz.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.eelve.springbootquartz.entity.ScheduleJobLogEntity;
import com.eelve.springbootquartz.page.PageUtils;

import java.util.Map;

/**
 * 定时任务日志
 */
public interface ScheduleJobLogPlusService extends IService<ScheduleJobLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void delete(Long id);
}
