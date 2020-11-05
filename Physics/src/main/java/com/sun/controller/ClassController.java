package com.sun.controller;

import com.sun.entity.ClassRoom;
import com.sun.entity.Teacher;
import com.sun.service.ClassService;
import com.sun.utils.PageUtil;
import com.sun.utils.PageUtils;
import com.sun.utils.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;


/**
 * @author 超雨
 * @create 2020--10--14--12:22
 */
@Controller
@RequestMapping("/class")
public class ClassController {
    //controller层调用service层
    @Autowired
    @Qualifier("ClassServiceImpl")
    ClassService classService;

    //获取班级信息  分页、模糊查询
    public String queryByPaging(String className, String pageNo, Model model){
        PageUtils<ClassRoom> pageUtils = new PageUtils<ClassRoom>(null);
        if(pageNo != null){
            pageUtils = new PageUtils<ClassRoom>(Integer.parseInt(pageNo));
        }
        pageUtils = classService.queryByPaging(className,pageUtils.getPageNo());
        model.addAttribute("pageList",pageUtils);
        return "class/list";
    }

    @RequestMapping("/toList")
    public String toList(){
        return "classRoom/list";
    }

    //获取班级信息
    //获取所有教师的信息
    @RequestMapping("/classList")
    @ResponseBody
    public RestResponse List(Integer page, Integer limit, @Nullable ClassRoom classRoom){
        List<ClassRoom> classRoomList = classService.getAllClassRoom(classRoom);
        if(classRoomList.size() != 0){
            HashMap hashMap = PageUtil.PageByList(classRoomList,page,limit,classRoomList.size());
            return  RestResponse.ok(hashMap);
        }
        return RestResponse.fail(200,"请求数据异常");
    }
}
