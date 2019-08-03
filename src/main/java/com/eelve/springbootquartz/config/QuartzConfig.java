package com.eelve.springbootquartz.config;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.eelve.springbootquartz.job.Job;

/**
 * @ClassName QuartzConfig
 * @Description TDO
 * @Author zhao.zhilue
 * @Date 2019/8/2 16:53
 * @Version 1.0
 **/
@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail testQuartz1() {
        return JobBuilder.newJob(Job.class).withIdentity("testTask1","test").withDescription("ceshices").storeDurably().build();
    }

    @Bean
    public Trigger testQuartzTrigger1() {
        //5秒执行一次
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(testQuartz1())
                .withIdentity("testTask1","test")
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Bean
    public JobDetail testQuartz2() {
        return JobBuilder.newJob(Job.class).withIdentity("testTask2","test").withDescription("ceshices").storeDurably().build();
    }

    @Bean
    public Trigger testQuartzTrigger2() {
        //cron方式，每隔5秒执行一次
        return TriggerBuilder.newTrigger().forJob(testQuartz2())
                .withIdentity("testTask2","test")
                .withSchedule(CronScheduleBuilder.cronSchedule("17 * * * * ?"))
                .build();
    }

}
