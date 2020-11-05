package com.sun.service;

import com.sun.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 超雨
 * @create 2020--10--02--20:13
 */
public interface StudentService {
    //学生登录信息
    Student login(String userName,String passWord);

    //查询所有学生信息
    List<Student> queryByAllStudent();

    //根据学号查询学生信息
    Student queryByStuNo(String stuNo);

    //更新学生信息
    Integer updateStudent(Student student);

    //增加学生信息
    Integer addStudent(Student student);

    //删除学生信息
    Integer deleteByStuNo(String stuNo);

    //查询所有学生信息
    List<Student> queryAllStudent(Student student);

    //批量删除学生信息
    void batchDelete(String arrayStr);
}
