package com.sun.controller;

import com.sun.entity.Trial;
import com.sun.entity.Vision;
import com.sun.mapper.VisionMapper;
import com.sun.service.VisionService;
import com.sun.utils.PageUtil;
import com.sun.utils.PageUtils;
import com.sun.utils.ParseInt;
import com.sun.utils.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * @author 超雨
 * @create 2020--10--08--16:56
 */
@RequestMapping("/vision")
@Controller
public class VisionController {

    //controller层调用mapper层方法
    @Autowired
    @Qualifier("VisionServiceImpl")
    VisionService visionService;

    //获取所有的实验视频 附带分页  模糊查询
    @RequestMapping("/allVision")
    public String getAllVision(Model model, String visionName, String pageNo){
        //创建分页对象
        PageUtils<Vision> pageUtils = visionService.getAllVision(ParseInt.getInteger(pageNo),visionName);
        //将分页对象传入到视图中
        model.addAttribute("pageList",pageUtils);
        //回显数据
        model.addAttribute("visionName",visionName);
        return "/vision/more";
    }

    //跳转到查看视频的页面
    @RequestMapping("/show/{visionID}")
    public String show(Model model, @PathVariable("visionID") Integer visionID){
        model.addAttribute("vision",visionService.getVisionByID(visionID));
        return "/vision/vision";
    }

    @RequestMapping("toList")
    public String  toList(){
        return "vision/list";
    }

    //获取所有视频的信息
    @RequestMapping("/visionList")
    @ResponseBody
    public RestResponse List(Integer page, Integer limit, @Nullable Vision vision){
        List<Vision> visionList = visionService.getAllVisions(vision);
        if(visionList.size() != 0){
            HashMap hashMap = PageUtil.PageByList(visionList,page,limit,visionList.size());
            return  RestResponse.ok(hashMap);
        }
        return RestResponse.fail(200,"请求数据异常");
    }

    //跳转到添加视频的页面
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "vision/add";
    }

    //增加视频教程信息
    @RequestMapping("/addVision")
    public String addVision(Vision vision){
        visionService.addVision(vision);
        return "";
    }

}
