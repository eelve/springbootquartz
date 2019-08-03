package com.eelve.springbootquartz.dao;

import com.eelve.springbootquartz.entity.TaskEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName TaskDao
 * @Description TDO
 * @Author zhao.zhilue
 * @Date 2019/8/1 17:19
 * @Version 1.0
 **/
@Mapper
public interface TaskDao {
    /**
     * 获取所有任务列表
     * @return
     */
    List<TaskEntity> findTaskList();
}
