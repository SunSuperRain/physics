package com.sun.controller;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sun.entity.Admin;
import com.sun.entity.Teacher;
import com.sun.service.RoleService;
import com.sun.service.TeacherService;
import com.sun.utils.PageUtil;
import com.sun.utils.PageUtils;
import com.sun.utils.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.management.relation.Role;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 超雨
 * @create 2020--10--13--8:52
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {
    //controller层调用service层
    @Autowired
    @Qualifier("TeacherServiceImpl")
    TeacherService teacherService;

    @Autowired
    @Qualifier("RoleServiceImpl")
    RoleService roleService;

    @RequestMapping("/login")
    public String login(String card,String userName, String passWord, HttpServletRequest request, Model model){
        Teacher teacher = teacherService.login(userName, passWord);
        if(teacher != null){
            HttpSession session = request.getSession();
            //存入用户权限信息
            session.setAttribute("card",card);
            session.setAttribute("teacher",teacher);
            //存入用户姓名信息
            session.setAttribute("name",teacher.getTeacherName());

            //获取左侧菜单栏内容
            model.addAttribute("roleList",roleService.getRoles(Integer.parseInt(card)));

            //登录到首页
            return "index";
        }else {
            //用户信息错误
            model.addAttribute("message", "用户不存在！");
            return "login";
        }
    }

    //跳转到编辑页面   附加教师信息
    @RequestMapping("/toEdit")
    public String toEdit(Integer tId,Model model){
        model.addAttribute("teacher",teacherService.queryById(tId));
        return "teacher/edit";
    }

    //更新教师信息
    @RequestMapping("/updateATeacher")
    public String edit(Teacher teacher){
        teacherService.updateById(teacher);
        return "redirect: /VRPhysics/teacher/message?tId" + teacher.getTId();
    }

    //跳转到提供添加教师信息的方法
    @RequestMapping("/toAdd")
    public String toAddTeacher(){
        return "teacher/add";
    }

    //提供添加教师信息的方法
    @RequestMapping("/add")
    public String addTeacher(Teacher teacher){
        teacherService.addTeacher(teacher);
        return "teacher/list";
    }

    //提供删除教师信息的方法
    @RequestMapping("/delete")
    @ResponseBody
    public void deleteTeacher(Integer tId){
        teacherService.deleteById(tId);
    }

    @RequestMapping("toList")
    public String toList(){
        return "teacher/list";
    }

    //获取所有教师的信息
    @RequestMapping("/teacherList")
    @ResponseBody
    public RestResponse List(Integer page,Integer limit,@Nullable Teacher teacher){
        List<Teacher> teacherList = teacherService.queryAllTeacher(teacher);
       if(teacherList.size() != 0){
           HashMap hashMap = PageUtil.PageByList(teacherList,page,limit,teacherList.size());
           return  RestResponse.ok(hashMap);
        }
        return RestResponse.fail(200,"请求数据异常");
    }

    //批量删除教师信息
    @RequestMapping("/batchDelete")
    @ResponseBody
    public void batchDelete(String tIds){
        teacherService.batchDelete(tIds);
    }

    //获取个人信息
    @RequestMapping("/message")
    public String message(Integer tId,Model model){
        model.addAttribute("teacher",teacherService.queryById(tId));
        return "teacher/info";
    }

    //修改密码信息
    //跳转到更新密码的界面
    @RequestMapping("/toUpdatePassWord")
    public String toUpdatePassWord(){
        return "teacher/password";
    }

    //更新教师密码
    @RequestMapping("/updatePassWord")
    public String updatePassWord(Teacher teacher, Model model, String oldPassword){
        Teacher oldTeacher = new Teacher();
        oldTeacher.setTId(teacher.getTId());
        oldTeacher.setPassWord(oldPassword);
        if(null != teacherService.queryByPassWord(oldTeacher)){
            //表明存在此用户   进行修改密码的操作
            teacherService.updatePassWord(teacher);
            model.addAttribute("success","密码修改成功！");
            return "teacher/password";
        }else {
            //不存在此用户  提示错误信息
            model.addAttribute("failure","当前密码输入错误");
            return "teacher/password";
        }
    }

    //处理用户退出的功能
    @RequestMapping("/quit")
    public String quit(HttpServletRequest request){
        //销毁session域中的管理员信息   并返回到登录页面
        request.getSession().invalidate();
        return  "redirect: /VRPhysics/student/test";
    }

}
