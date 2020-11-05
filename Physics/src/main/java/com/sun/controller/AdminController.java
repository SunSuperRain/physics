package com.sun.controller;

import com.sun.entity.Admin;
import com.sun.service.AdminService;
import com.sun.service.RoleService;
import com.sun.service.TrialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author 超雨
 * @create 2020--10--13--7:56
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    //controller层调用service层方法
    @Autowired
    @Qualifier("AdminServiceImpl")
    AdminService adminService;

    @Autowired
    @Qualifier("TrialServiceImpl")
    TrialService trialService;

    @Autowired
    @Qualifier("RoleServiceImpl")
    RoleService roleService;


    //验证管理员登录信息
    @RequestMapping("/login")
    public String login(String card,String userName, String passWord, HttpServletRequest request, Model model){
        Admin admin = adminService.login(userName, passWord);
        if(null != admin){
            //表明用户存在
            HttpSession session = request.getSession();
            //获取用户权限信息
            session.setAttribute("card",card);
            session.setAttribute("admin",admin);

            //存入用户姓名信息
            session.setAttribute("name",admin.getAdminName());

            model.addAttribute("roleList",roleService.getRoles(Integer.parseInt(card)));

            //返回到显示页面
            return "index";
        }else {
            //用户不存在   返回错误信息   返回登录页面
            model.addAttribute("message","用户不存在");
            return "login";
        }
    }

    //根据管理员ID获取管理员信息
    @RequestMapping("/message")
    public String getById(Integer aId,Model model){
        Admin admin = adminService.getByID(aId);
        model.addAttribute("admin",admin);
        return "admin/info";
    }

    //更新管理员信息
    @RequestMapping("/updateAdmin")
    public String updateAdmin(Admin admin){
        adminService.updateAdmin(admin);
        return "redirect: /VRPhysics/admin/message?aId=" + admin.getAId();
    }

    //跳转到更新密码的界面
    @RequestMapping("/toUpdatePassWord")
    public String toUpdatePassWord(){
        return "admin/password";
    }

    //处理用户退出的功能
    @RequestMapping("/quit")
    public String quit(HttpServletRequest request, HttpServletResponse response){
        //销毁session域中的管理员信息   并返回到登录页面
        request.getSession().invalidate();
        return "redirect: /VRPhysics/student/test";
    }

    //更新管理员密码
    @RequestMapping("/updatePassWord")
    public String updatePassWord(Admin admin,Model model,String oldPassword){
        Admin oldAdmin = new Admin();
        oldAdmin.setAId(admin.getAId());
        oldAdmin.setPassWord(oldPassword);
        if(null != adminService.queryByPassWord(oldAdmin)){
            //表明存在此用户   进行修改密码的操作
            adminService.updatePassWord(admin);
            model.addAttribute("success","密码修改成功！");
            return "admin/password";
        }else {
            //不存在此用户  提示错误信息
            model.addAttribute("failure","当前密码输入错误");
            return "admin/password";
        }
    }
}
