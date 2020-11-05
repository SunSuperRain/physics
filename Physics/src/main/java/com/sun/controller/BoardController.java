package com.sun.controller;

import com.sun.entity.Admin;
import com.sun.entity.Board;

import com.sun.service.AdminService;
import com.sun.service.BoardService;
import com.sun.utils.PageUtil;
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
 * @author Sun
 * @version 1.0
 * @date 2020/10/26 18:14
 */
@Controller
@RequestMapping("/board")
public class BoardController {
    //controller层调用service层
    @Autowired
    @Qualifier("BoardServiceImpl")
    BoardService boardService;

    @Autowired
    @Qualifier("AdminServiceImpl")
    AdminService adminService;


    //获取所有的评论信息
    @RequestMapping("/toList")
    public String toList(){
        return "admin/message";
    }

    //获取所有教师的信息
    @RequestMapping("/boardList")
    @ResponseBody
    public RestResponse List(Integer page, Integer limit, Integer aId){
        List<Board> boardList = boardService.getAllBoards(aId);
        if(boardList.size() != 0){
            HashMap hashMap = PageUtil.PageByList(boardList,page,limit,boardList .size());
            return  RestResponse.ok(hashMap);
        }
        return RestResponse.fail(200,"请求数据异常");
    }

    //进入提交申请的页面
    @RequestMapping("/toSubmit")
    public String toSubmit(Model model){
        //获取所有的管理员信息
        model.addAttribute("adminList",adminService.getAllAdmin());
        return "teacher/submit";
    }

    //提交申请的方法
    @RequestMapping("/submit")
    public String submit(Board board){
        board.setAdminName(adminService.getByID(board.getAId()).getAdminName());
        boardService.addBoard(board);
        return "redirect: /VRPhysics/board/toSubmit";
    }
}
