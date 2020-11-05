package com.sun.controller;

import com.sun.entity.Reply;
import com.sun.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 超雨
 * @create 2020--10--07--11:35
 */
@Controller
@RequestMapping("/reply")
public class ReplyController {
    //controller层调用service层方法
    @Autowired
    @Qualifier("ReplyServiceImpl")
    ReplyService replyService;

    //获取所有的评论
    @RequestMapping("/allReply")
    public String getAllReply(Model model){
        model.addAttribute("list",replyService.getAllReply());
        return "/essay/comment";
    }

    //发表评论内容
    @RequestMapping("/addReply")
    public String addReply(Reply reply){
        //获取系统当前时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        reply.setReplyTime(simpleDateFormat.format(date));
        replyService.addReply(reply);
        return "redirect: /VRPhysics/reply/allReply";
    }

    //更新评论点赞数目
    @RequestMapping("/updateFan/{replyID}")
    public String updateFan(@PathVariable("replyID") Integer replyID){
        replyService.updateFan(replyID);
        return "redirect: /VRPhysics/reply/allReply";
    }

}
