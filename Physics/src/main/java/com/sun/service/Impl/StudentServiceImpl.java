package com.sun.service.Impl;

import com.google.gson.Gson;
import com.sun.entity.Student;
import com.sun.mapper.StudentMapper;
import com.sun.service.StudentService;
import org.apache.ibatis.annotations.Param;
import sun.net.idn.Punycode;

import java.util.List;

/**
 * @author 超雨
 * @create 2020--10--02--20:14
 */
public class StudentServiceImpl implements StudentService {

    //service层调用mapper层
    private StudentMapper studentMapper;

    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    //学生登录信息
    public Student login(String userName, String passWord){
        return studentMapper.login(userName,passWord);
    }

    public List<Student> queryByAllStudent() {
        return studentMapper.queryByAllStudent();
    }

    public Student queryByStuNo(String stuNo) {
        return studentMapper.queryByStuNo(stuNo);
    }

    public Integer updateStudent(Student student) {
        return studentMapper.updateStudent(student);
    }

    public Integer addStudent(Student student) {
        return studentMapper.addStudent(student);
    }

    public Integer deleteByStuNo(String stuNo) {
        return studentMapper.deleteByStuNo(stuNo);
    }

    @Override
    public List<Student> queryAllStudent(Student student) {
        return studentMapper.queryAllStudent(student);
    }

    @Override
    public void batchDelete(String arrayStr) {
        Gson gson = new Gson();
        Integer[] stuNos = gson.fromJson(arrayStr,Integer[].class);
    }
}
