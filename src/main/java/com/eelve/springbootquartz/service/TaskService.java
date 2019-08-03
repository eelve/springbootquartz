package com.eelve.springbootquartz.service;

import com.eelve.springbootquartz.entity.TaskEntity;

import java.util.List;

/**
 * @author zhao.zhilue
 * @Description: findAllTask
 * @date 2019/8/117:24
 */
public interface TaskService {

    List<TaskEntity> findAllTask();
}
