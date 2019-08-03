package com.eelve.springbootquartz.controller;

import com.eelve.springbootquartz.entity.TaskEntity;
import com.eelve.springbootquartz.service.QuartzService;
import com.eelve.springbootquartz.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ClassName TaskController
 * @Description TDO
 * @Author zhao.zhilue
 * @Date 2019/6/14 10:46
 * @Version 1.0
 **/
@Controller
public class TaskController {

    @Autowired
    private QuartzService quartzService;

    @Autowired
    private TaskService taskService;


    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }

    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page,
                       @RequestParam(value = "size", defaultValue = "6") Integer size) {
        List<TaskEntity> tasks=taskService.findAllTask();
        model.addAttribute("tasks", tasks);
        return "list";
    }

}
