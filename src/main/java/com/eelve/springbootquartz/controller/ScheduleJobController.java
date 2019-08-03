
package com.eelve.springbootquartz.controller;


import com.eelve.springbootquartz.entity.ScheduleJobEntity;
import com.eelve.springbootquartz.page.PageUtils;
import com.eelve.springbootquartz.service.ScheduleJobService;
import com.eelve.springbootquartz.utils.JsonResultUtil;
import com.eelve.springbootquartz.validator.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 定时任务
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleJobController{
    @Autowired
    private ScheduleJobService scheduleJobService;

    /**
     * 定时任务列表
     */
    @GetMapping("/list")
    public JsonResultUtil<PageUtils<ScheduleJobEntity>> list(@RequestParam Map<String, Object> params) {
        PageUtils<ScheduleJobEntity> page = scheduleJobService.queryPage(params);
        return JsonResultUtil.ok().put(page);
    }

    /**
     * 定时任务信息
     */
    @GetMapping("/info/{jobId}")
     public JsonResultUtil<ScheduleJobEntity> info(@PathVariable("jobId") Long jobId) {
        ScheduleJobEntity schedule = scheduleJobService.getById(jobId);
        return JsonResultUtil.ok().put(schedule);
    }

    /**
     * 保存定时任务
     */
    @PostMapping("/save")
    public JsonResultUtil<Void> save(@RequestBody ScheduleJobEntity scheduleJob) {
        ValidatorUtils.validateEntity(scheduleJob);
        scheduleJobService.add(scheduleJob);
        return JsonResultUtil.ok();
    }

    /**
     * 修改定时任务
     */

    @PostMapping("/update")
    public JsonResultUtil<Void> update(@RequestBody ScheduleJobEntity scheduleJob) {
        ValidatorUtils.validateEntity(scheduleJob);
        scheduleJobService.update(scheduleJob);
        return JsonResultUtil.ok();
    }

    /**
     * 删除定时任务
     */
    @PostMapping("/delete")
    public JsonResultUtil<Void> delete(@RequestBody Long[] jobIds) {
        scheduleJobService.deleteBatch(jobIds);
        return JsonResultUtil.ok();
    }

    /**
     * 立即执行任务
     */
    @PostMapping("/run")
    public JsonResultUtil<Void> run(@RequestBody Long[] jobIds) {
        scheduleJobService.run(jobIds);
        return JsonResultUtil.ok();
    }

    /**
     * 暂停定时任务
     */
    @PostMapping("/pause")
    public JsonResultUtil<Void> pause(@RequestBody Long[] jobIds) {
        scheduleJobService.pause(jobIds);
        return JsonResultUtil.ok();
    }

    /**
     * 恢复定时任务
     */
    @PostMapping("/resume")
    public JsonResultUtil<Void> resume(@RequestBody Long[] jobIds) {
        scheduleJobService.resume(jobIds);
        return JsonResultUtil.ok();
    }

}
