package com.eelve.springbootquartz.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName TaskEntity
 * @Description TDO
 * @Author zhao.zhilue
 * @Date 2019/8/1 16:52
 * @Version 1.0
 **/
@Data
public class TaskEntity  implements Serializable {
    private final Long serialVersion = -12654128415L;
    private Long id; //ID
    private String jobName; //任务名称
    private String jobGroup; //任务分组
    private String jobStatus; //任务状态
    private String jobClass;//任务执行方法
    private String cronExpression; // cron 表达式
    private String jobDescription; //任务描述
    private String timeZoneId; // 时区
    private Long startTime;
    private Long endTime;
    private String state; //状态
}
