package com.sun.controller;

import com.sun.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Sun
 * @version 1.0
 * @date 2020/10/29 11:37
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    //controller层调用service层
    @Autowired
    @Qualifier("TrialServiceImpl")
    TrialService trialService;

    @Autowired
    @Qualifier("AdminServiceImpl")
    AdminService adminService;

    @Autowired
    @Qualifier("StudentServiceImpl")
    StudentService studentService;

    @Autowired
    @Qualifier("TeacherServiceImpl")
    TeacherService teacherService;

    @Autowired
    @Qualifier("VisionServiceImpl")
    VisionService visionService;

    @Autowired
    @Qualifier("ClassServiceImpl")
    ClassService classService;

    //整合所有的信息 到首页信息
    @RequestMapping("/toHome")
    public String toHome(Model model){
        //获取所有实验的点击次数
        model.addAttribute("trialCounts",trialService.getAllCounts());
        //获取管理员人数
        model.addAttribute("adminCounts",adminService.getAllAdmin().size());
        //获取学生人数
        model.addAttribute("studentCounts",studentService.queryAllStudent(null).size());
        //教师教师人数
        model.addAttribute("teacherCounts",teacherService.queryAllTeacher(null).size());
        //获取实验数目
        model.addAttribute("trialTotal",trialService.getAllTrials(null).size());
        //获取视频数目
        model.addAttribute("visionCounts",visionService.getAllVisions(null).size());
        //获取班级数目
        model.addAttribute("classCounts",classService.getAllClassRoom(null).size());
        return "home/homepage";
    }
}
