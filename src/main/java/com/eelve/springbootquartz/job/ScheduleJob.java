package com.eelve.springbootquartz.job;


import com.eelve.springbootquartz.entity.ScheduleJobEntity;
import com.eelve.springbootquartz.entity.ScheduleJobLogEntity;
import com.eelve.springbootquartz.service.ScheduleJobLogService;
import com.eelve.springbootquartz.utils.SpringContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;


/**
 * 定时任务
 */
@Slf4j
public class ScheduleJob extends QuartzJobBean {
    //private ExecutorService service = Executors.newSingleThreadExecutor();
    private ScheduledExecutorService service = new ScheduledThreadPoolExecutor(1, new BasicThreadFactory.Builder().namingPattern("quartz-job-schedule-pool-%d").daemon(true).build());

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        ScheduleJobEntity scheduleJob = (ScheduleJobEntity) context.getMergedJobDataMap().get(ScheduleJobEntity.JOB_PARAM_KEY);
        if (scheduleJob==null){
            log.error("任务执行失败，未知任务信息");
            return;
        }

        //获取spring bean
        ScheduleJobLogService scheduleJobLogService = (ScheduleJobLogService) SpringContext.getBean("scheduleJobLogService");
        //数据库保存执行记录
        ScheduleJobLogEntity logEntity = new ScheduleJobLogEntity();
        logEntity.setJobId(scheduleJob.getJobId());
        logEntity.setClassName(scheduleJob.getClassName());
        logEntity.setMethodName(scheduleJob.getMethodName());
        logEntity.setParams(scheduleJob.getParams());
        logEntity.setCreateTime(new Date());

        //任务开始时间
        long startTime = System.currentTimeMillis();

        try {
            //执行任务
            log.info("任务准备执行，任务ID：" + scheduleJob.getJobId());
            ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getClassName(), scheduleJob.getMethodName(), scheduleJob.getParams());

            Future<?> future = service.submit(task);
            future.get();
            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            logEntity.setTimes((int) times);
            //任务状态    0：成功    1：失败
            logEntity.setStatus(0);
            log.info("任务执行完毕，任务ID：" + scheduleJob.getJobId() + "  总共耗时：" + times + "毫秒");
        } catch (Exception e) {
            log.error("任务执行失败，任务ID：" + scheduleJob.getJobId(), e);
            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            logEntity.setTimes((int) times);
            //任务状态    0：成功    1：失败
            logEntity.setStatus(1);
            logEntity.setError(StringUtils.substring(e.toString(), 0, 2000));
        } finally {
            logEntity.setCreateUserId(scheduleJob.getCreateUserId());
            scheduleJobLogService.saveLog(logEntity);
        }
    }
}
