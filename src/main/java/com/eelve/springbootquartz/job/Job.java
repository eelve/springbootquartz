package com.eelve.springbootquartz.job;

import com.eelve.springbootquartz.entity.ScheduleJobLogEntity;
import com.eelve.springbootquartz.service.QuartzService;
import com.eelve.springbootquartz.service.ScheduleJobLogPlusService;
import com.eelve.springbootquartz.service.ScheduleJobLogService;
import com.eelve.springbootquartz.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * @ClassName Job
 * @Description TDO
 * @Author zhao.zhilue
 * @Date 2019/7/31 15:52
 * @Version 1.0
 **/
@Slf4j
public class Job extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        ApplicationContext applicationContext = SpringUtils.getApplicationContext();

        QuartzService quartzService = (QuartzService)applicationContext.getBean("quartzService");
        //获取spring bean
        ScheduleJobLogPlusService scheduleJobLogService = (ScheduleJobLogPlusService) applicationContext.getBean("scheduleJobLogPlusService");


        // 获取参数
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        // 业务逻辑 ...
        log.info("------springbootquartzonejob执行###############"+jobExecutionContext.getTrigger());

        ScheduleJobLogEntity scheduleJobLogEntity = new ScheduleJobLogEntity();

        //任务开始时间
        long startTime = System.currentTimeMillis();
        try {
            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            scheduleJobLogEntity.setTimes((int)times);
            log.info("任务执行完毕，任务ID：" + jobExecutionContext.getJobDetail() + "  总共耗时：" + times + "毫秒");
        } catch (Exception e) {
            long times = System.currentTimeMillis() - startTime;
            log.error("任务执行失败，任务ID：" + jobExecutionContext.getJobDetail()+ "  总共耗时：" + times + "毫秒", e);
            scheduleJobLogEntity.setTimes((int)times);
        } finally {
            scheduleJobLogService.save(scheduleJobLogEntity);
        }
    }
}
