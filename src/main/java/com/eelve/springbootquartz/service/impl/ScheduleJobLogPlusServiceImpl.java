package com.eelve.springbootquartz.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eelve.springbootquartz.dao.ScheduleJobLogMapper;
import com.eelve.springbootquartz.entity.ScheduleJobLogEntity;
import com.eelve.springbootquartz.page.PageUtils;

import com.eelve.springbootquartz.page.Query;
import com.eelve.springbootquartz.service.ScheduleJobLogPlusService;
import com.eelve.springbootquartz.utils.QueryWrapperUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("scheduleJobLogPlusService")
public class ScheduleJobLogPlusServiceImpl extends ServiceImpl<ScheduleJobLogMapper, ScheduleJobLogEntity> implements ScheduleJobLogPlusService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String searchKey = (String) params.get("searchKey");

        QueryWrapper<ScheduleJobLogEntity> ew = QueryWrapperUtils.wrapperLike(new QueryWrapper<ScheduleJobLogEntity>(), searchKey, "job_id", "class_name", "params", "error");
//        if (UserUtils.isNotAdmin()) {
//            ew.and(wrapper -> wrapper.eq("create_user_id", UserUtils.getCurrentUserId()));
//        }
        // 根据时间查询
        String startTime = (String) params.getOrDefault("startTime", "");
        if (org.apache.commons.lang3.StringUtils.isNotBlank(startTime)) {
            ew.and(wrapper -> wrapper.ge("create_time", DateUtil.parse(startTime, DatePattern.NORM_DATETIME_PATTERN)));
        }
        String endTime = (String) params.getOrDefault("endTime", "");
        if (StringUtils.isNotBlank(endTime)) {
            ew.and(wrapper -> wrapper.le("create_time", DateUtil.parse(endTime, DatePattern.NORM_DATETIME_PATTERN)));
        }
        ew.orderByDesc("create_time");
        IPage<ScheduleJobLogEntity> page = this.page(new Query<ScheduleJobLogEntity>(params).getPage(), ew);
        return new PageUtils(page);
    }

    @Override
    public void delete(Long id) {
        baseMapper.deleteById(id);
    }

}
