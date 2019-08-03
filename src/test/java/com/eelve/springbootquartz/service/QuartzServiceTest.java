package com.eelve.springbootquartz.service;

import com.eelve.springbootquartz.constant.ScheduleStatus;
import com.eelve.springbootquartz.entity.ScheduleJobEntity;
import com.eelve.springbootquartz.job.Job;
import com.eelve.springbootquartz.utils.RedisUtil;
import com.eelve.springbootquartz.utils.ScheduleUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author zhao.zhilue
 * @Description:
 * @date 2019/7/3115:56
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class QuartzServiceTest {

    @Resource
    Scheduler scheduler;

   @Autowired
   QuartzService quartzService;

    @Autowired
    RedisUtil redisUtil;

    @Test
    public void addJob() {
        Map map = new HashMap(2);
        map.put("id", 1L);
        // 先删除，再新增加
        quartzService.deleteJob("job22", "test");
        quartzService.addJob(Job.class, "job22", "test", "5 * * * * ?", map);
    }

    @Test
    public void queryAllJob() {
        List<Map<String, Object>> list =  quartzService.queryAllJob();
        System.out.printf("我在查询所有任务"+list.toString());
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("我在查询所有任务"+list.get(i).toString());
        }
    }

    @Test
    public void add() {
        ScheduleJobEntity scheduleJob = new ScheduleJobEntity();

        scheduleJob.setCreateTime(new Date());
        scheduleJob.setStatus(ScheduleStatus.NORMAL.getValue());
        scheduleJob.setCreateUserId(1L);
        //this.save(scheduleJob);
        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
    }
}
