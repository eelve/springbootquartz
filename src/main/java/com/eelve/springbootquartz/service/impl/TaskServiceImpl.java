package com.eelve.springbootquartz.service.impl;

import com.eelve.springbootquartz.dao.TaskDao;
import com.eelve.springbootquartz.entity.TaskEntity;
import com.eelve.springbootquartz.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName TaskServiceImpl
 * @Description TDO
 * @Author zhao.zhilue
 * @Date 2019/8/1 17:25
 * @Version 1.0
 **/
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskDao taskDao;

    @Override
    public List<TaskEntity> findAllTask() {
        return taskDao.findTaskList();
    }
}
