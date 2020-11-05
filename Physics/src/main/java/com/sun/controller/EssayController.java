package com.sun.controller;

import com.sun.entity.Board;
import com.sun.entity.Essay;
import com.sun.entity.Great;
import com.sun.mapper.GreatMapper;
import com.sun.service.EssayService;
import com.sun.service.GreatService;
import com.sun.utils.PageUtil;
import com.sun.utils.PageUtils;
import com.sun.utils.ParseInt;


import com.sun.utils.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author 超雨
 * @create 2020--10--06--17:27
 */
@Controller
@RequestMapping("/essay")
public class EssayController {

    //controller层调用service层方法
    @Autowired
    @Qualifier("EssayServiceImpl")
    EssayService essayService;

    @Autowired
    @Qualifier("GreatServiceImpl")
    GreatService greatService;

    //根据分页  模糊 查询获取文章信息
    @RequestMapping("/allEssay")
    public String getAllEssay(Model model,String pageNo,String skill){
        //创建分页对象
        PageUtils<Essay> pageUtils = new PageUtils<Essay>(ParseInt.getInteger(pageNo));
        //获取数据信息
        model.addAttribute("pageList",essayService.queryByPaging(pageUtils.getPageNo(),skill));
        //回显查询关键词
        model.addAttribute("skill",skill);
        return "/essay/show";
    }

    //增加话题的方法
    @RequestMapping("/addEssay")
    public String addEssay(Essay essay){
        //获取系统当前时间  存入到对象中
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        essay.setEssayTime(simpleDateFormat.format(date));
        //加入话题
        essayService.addEssay(essay);

        //重定向到显示页面
        return "redirect: /VRPhysics/essay/allEssay";
    }

    //获取文章信息
    @RequestMapping("/getEssay/{essayID}")
    public String getByEssayID(@PathVariable("essayID") Integer essayID, HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("essay",essayService.getByEssayID(essayID));

        //文章浏览记录加一
        essayService.updateRecord(essayID);

        //重定向到获取评论的controller层
        return "redirect: /VRPhysics/reply/allReply";
    }

    //更新文章赞数信息
    @RequestMapping("/updateFans")
    @ResponseBody
    public String updateFans(String stuNo,String essayID){
        //获取该文章是否点赞
        //更新用户点赞文章信息
        essayService.updateFans(stuNo,ParseInt.getInteger(essayID));
        //获取当前用户信息的赞数 并返回
        String fans = essayService.getByEssayID(ParseInt.getInteger(essayID)).getFans().toString();
        return fans;
    }

    //获取所有的文章信息
    @RequestMapping("/toList")
    public String toList(){
        return "essay/list";
    }

    //获取文章信息
    @RequestMapping("/essayList")
    @ResponseBody
    public RestResponse List(Integer page, Integer limit, @Nullable Essay essay){
        List<Essay> essayList = essayService.getAllEssay(essay);
        if(essayList.size() != 0){
            HashMap hashMap = PageUtil.PageByList(essayList,page,limit,essayList .size());
            return  RestResponse.ok(hashMap);
        }
        return RestResponse.fail(200,"请求数据异常");
    }

    //根据文章编号删除文章信息
    @RequestMapping("/delete")
    @ResponseBody
    public void delete(Integer essayID){
        essayService.deleteById(essayID);
    }
}
