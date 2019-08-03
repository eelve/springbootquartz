
package com.eelve.springbootquartz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eelve.springbootquartz.constant.ScheduleStatus;
import com.eelve.springbootquartz.dao.ScheduleJobDao;
import com.eelve.springbootquartz.entity.ScheduleJobEntity;
import com.eelve.springbootquartz.page.PageUtils;
import com.eelve.springbootquartz.page.Query;
import com.eelve.springbootquartz.service.ScheduleJobService;
import com.eelve.springbootquartz.utils.QueryWrapperUtils;
import com.eelve.springbootquartz.utils.ScheduleUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.common.collect.Lists;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

@Service("scheduleJobService")
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobDao, ScheduleJobEntity> implements ScheduleJobService {
    @Resource
    private Scheduler scheduler;



    /**
     * 项目启动时，初始化定时器
     */
    @PostConstruct
    public void init() {
        List<ScheduleJobEntity> scheduleJobList = this.list(null);
        for (ScheduleJobEntity scheduleJob : scheduleJobList) {
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
            //如果不存在，则创建
            if (cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            } else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        }
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String searchKey = (String) params.get("searchKey");
        QueryWrapper<ScheduleJobEntity> ew = QueryWrapperUtils.wrapperLike(new QueryWrapper<ScheduleJobEntity>(), searchKey, "class_name", "method_name", "params", "remark", "job_id");
//        if (UserUtils.isNotAdmin()) {
//            ew.and(wrapper -> wrapper.eq("create_user_id", UserUtils.getCurrentUserId()));
//        }
        // 根据用户查询
        ew.orderByDesc("create_time");
        IPage<ScheduleJobEntity> page = this.page(new Query<ScheduleJobEntity>(params).getPage(), ew);
        return new PageUtils(page);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(ScheduleJobEntity scheduleJob) {
        scheduleJob.setCreateTime(new Date());
        scheduleJob.setStatus(ScheduleStatus.NORMAL.getValue());
        //scheduleJob.setCreateUserId(UserUtils.getCurrentUserId());
        this.save(scheduleJob);
        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ScheduleJobEntity scheduleJob) {
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);

        this.updateById(scheduleJob);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] jobIds) {
        for (Long jobId : jobIds) {
            ScheduleUtils.deleteScheduleJob(scheduler, jobId);
        }
        //删除数据
        this.removeByIds(Arrays.asList(jobIds));
    }

    @Override
    public int updateBatch(Long[] jobIds, int status) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", Lists.newArrayList(jobIds));
        map.put("status", status);
        return baseMapper.updateBatch(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void run(Long[] jobIds) {
        for (Long jobId : jobIds) {
            ScheduleUtils.run(scheduler, this.getById(jobId));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pause(Long[] jobIds) {
        for (Long jobId : jobIds) {
            ScheduleUtils.pauseJob(scheduler, jobId);
        }

        updateBatch(jobIds, ScheduleStatus.PAUSE.getValue());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resume(Long[] jobIds) {
        for (Long jobId : jobIds) {
            ScheduleUtils.resumeJob(scheduler, jobId);
        }
        updateBatch(jobIds, ScheduleStatus.NORMAL.getValue());
    }

}
