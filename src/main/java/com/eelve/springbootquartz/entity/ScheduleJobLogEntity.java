package com.eelve.springbootquartz.entity;



import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务日志
 */
@Data
public class ScheduleJobLogEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 日志id
     */
    private Long logId;

    /**
     * 任务id
     */
    private Long jobId;


    private String className;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 参数
     */
    private String params;

    /**
     * 任务状态    0：成功    1：失败
     */
    private Integer status;

    /**
     * 失败信息
     */
    private String error;

    /**
     * 耗时(单位：毫秒)
     */
    private Integer times;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建者ID
     */
    private Long createUserId;

}
