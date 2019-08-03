package com.eelve.springbootquartz.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @ClassName Task
 * @Description TDO
 * @Author zhao.zhilue
 * @Date 2019/8/2 9:20
 * @Version 1.0
 **/
@Component
public class Task {

    @Scheduled(cron = "5 0 0 * * ?")
    public void scheduledTask1(){
        System.out.println("#################scheduledTask method run..");
    }

    @Scheduled(initialDelay =  1000 * 10,fixedDelay = 1000 * 5)
    public void scheduledTask2(){
        System.out.println("###############scheduledTask2 method run..");
    }

}
