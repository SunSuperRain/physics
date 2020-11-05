package com.sun.controller;

import com.sun.entity.Student;
import com.sun.entity.Teacher;
import com.sun.service.ClassService;
import com.sun.service.StudentService;
import com.sun.service.TeacherService;
import com.sun.service.TrialService;
import com.sun.utils.PageUtil;
import com.sun.utils.RestResponse;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.text.normalizer.UBiDiProps;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * @author 超雨
 * @create 2020--10--02--20:16
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    //controller层调用service层方法
    @Autowired
    @Qualifier("StudentServiceImpl")
    StudentService studentService;

    @Autowired
    @Qualifier("TeacherServiceImpl")
    TeacherService teacherService;

    @Autowired
    @Qualifier("ClassServiceImpl")
    ClassService classService;

    //跳转到用户登录页面
    @RequestMapping("/test")
    public String toLogin(){
        return "login";
    }

    //编写用户登录校验
    @RequestMapping("/login")
    public String login(String userName,String passWord, Model model, HttpServletRequest request){
        //登录验证用户信息
        Student student = studentService.login(userName,passWord);
        //获取session作用域
        HttpSession httpSession = request.getSession();
        if (null != student){
            //个人信息  存入到session域中
            httpSession.setAttribute("student",student);

            //跳转到实验的控制层
            return "redirect: /VRPhysics/trial/list";
        }else {
            //登录失败   返回登录页面  并返回登录失败信息
            model.addAttribute("message","信息错误，登录失败");
            return "login";
        }
    }

    //用户退出登录
    @RequestMapping("/quit")
    public String quit(Model model,HttpServletRequest request){
        //用户退出登录  返回登录页面   并提升信息
        model.addAttribute("message","退出登录成功！");
        //销毁session的作用域
        request.getSession().invalidate();
        return "login";
    }

    //用户查看个人信息
    @RequestMapping("/message/{stuNo}")
    public String message(@PathVariable String stuNo,Model model){
        //根据学号获取学生个人信息
        model.addAttribute("student",studentService.queryByStuNo(stuNo));
        return null;
    }

    //进入实验页面
    @RequestMapping("/trail")
    public String into(){
        //跳转到实验页面
        return "trial/start";
    }

    //获取所有的学生信息
    @RequestMapping("/studentList")
    @ResponseBody
    public RestResponse List(Integer page, Integer limit, @Nullable Student student){
        List<Student> studentList = studentService.queryAllStudent(student);
        if(studentList != null){
            HashMap hashMap = PageUtil.PageByList(studentList,page,limit,studentList.size());
            return  RestResponse.ok(hashMap);
        }
        return RestResponse.fail(200,"请求数据异常");
    }

    //批量删除学生信息
    @RequestMapping("/batchDelete")
    @ResponseBody
    public void batchDelete(String arrayStr){
        studentService.batchDelete(arrayStr);
    }

    @RequestMapping("/toList")
    public String toList(){
        return "student/list";
    }

    //跳转到增加页面
    @RequestMapping("/toAdd")
    public String toAdd(Model model){
        //获取所有的班级和教师  数据的回显
        model.addAttribute("teacherList",teacherService.queryAllTeacher(null));
        model.addAttribute("classList",classService.getAllClassRoom(null));
        return "student/add";
    }

    //增加学生信息的方法
    @RequestMapping("addStudent")
    public String addStudent(Student student){
        studentService.addStudent(student);
        //更新班级信息
        classService.updateById(student.getClassNo());
        return "student/list";
    }

    //跳转到编辑页面
    @RequestMapping("toEdit")
    public String toEdit(String stuNo,Model model){
        //获取学生信息   数据回显
        model.addAttribute("student",studentService.queryByStuNo(stuNo));
        return "student/edit";
    }

    //修改学生信息
    @RequestMapping("/edit")
    public String edit(Student student){
        studentService.updateStudent(student);
        return "student/list";
    }

    //删除学生信息的方法
    @RequestMapping("delete")
    public void delete(String stuNo){
        studentService.deleteByStuNo(stuNo);
    }
}
