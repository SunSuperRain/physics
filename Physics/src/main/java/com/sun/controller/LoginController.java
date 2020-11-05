package com.sun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 超雨
 * @create 2020--10--08--14:45
 */
//处理用户登录的控制层
    @Controller
public class LoginController {

        @RequestMapping("/")
        public String login(){
        return "login";
    }

    //根据权限信息跳转到不同的页面
    @RequestMapping("/loginByRole")
    public String loginByRole(String card, String userName, String passWord){
            if ("0".equals(card)){
                //表明用户为管理员身份  跳转到管理员的controller
                return "redirect: /VRPhysics/admin/login?userName=" + userName + "&passWord=" + passWord + "&card=" + card;
            }
            if("1".equals(card)){
                //表明用户为教师身份  跳转到教师的controller
                return "redirect: /VRPhysics/teacher/login?userName=" + userName + "&passWord=" + passWord + "&card=" + card;
            }
            if("2".equals(card)){
                //表明用户为学生身份  跳转到学生的controller
                return "redirect: /VRPhysics/student/login?userName=" + userName + "&passWord=" + passWord;
            }
            return "";
    }
}
