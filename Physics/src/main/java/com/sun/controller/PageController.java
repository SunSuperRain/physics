package com.sun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 超雨
 * @create 2020--10--08--11:43
 */
//处理页面跳转的controller
//利用重定向跳转到controller层
@Controller
@RequestMapping("/page")
public class PageController {
    //跳转到网站首页
    @RequestMapping("/index")
    public String toIndex(){
        return "redirect: /VRPhysics/trial/list";
    }

    //跳转到实训项目
    @RequestMapping("/train")
    public String toTrain(){
        return "redirect: /VRPhysics/trial/allList";
    }

    //跳转到视频教程
    @RequestMapping("/vision")
    public String toVision(){
        return "/";
    }
}
