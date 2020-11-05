package com.sun.service.Impl;

import com.google.gson.Gson;
import com.sun.entity.Teacher;
import com.sun.mapper.TeacherMapper;
import com.sun.service.TeacherService;
import com.sun.utils.PageUtils;

import java.util.List;

/**
 * @author 超雨
 * @create 2020--10--13--8:50
 */
public class TeacherServiceImpl implements TeacherService {

    //service层调用dao层方法
    TeacherMapper teacherMapper;

    public void setTeacherMapper(TeacherMapper teacherMapper) {
        this.teacherMapper = teacherMapper;
    }

    public Teacher login(String userName, String passWord) {
        return teacherMapper.login(userName, passWord);
    }

    //进行逻辑代码的处理
    public PageUtils<Teacher> queryByPaging(Integer pageNo, Teacher teacher) {
        PageUtils<Teacher> pageUtils = new PageUtils<Teacher>(pageNo);
        pageUtils.setList(teacherMapper.queryByPaging(teacher.getTeacherName(),teacher.getPhone(),(pageUtils.getPageNo()-1)*pageUtils.getPageSize(),pageUtils.getPageSize()));
        pageUtils.setTotalCount(teacherMapper.queryTotal(teacher.getTeacherName(),teacher.getPhone()));
        return pageUtils;
    }

    public Integer queryTotal(Teacher teacher) {
        return teacherMapper.queryTotal(teacher.getTeacherName(),teacher.getPhone());
    }

    public void updateById(Teacher teacher) {
        teacherMapper.updateById(teacher);
    }

    public void deleteById(Integer tId) {
        teacherMapper.deleteById(tId);
    }

    public void addTeacher(Teacher teacher) {
        teacherMapper.addTeacher(teacher);
    }

    public List<Teacher> queryAllTeacher(Teacher teacher) {
        return teacherMapper.queryAllTeacher(teacher);
    }

    @Override
    public Teacher queryById(Integer tId) {
        return teacherMapper.queryById(tId);
    }

    @Override
    public void batchDelete(String tIds) {
        //进行业务的处理     字符串转化为数组类型
        Gson gson = new Gson();
        Integer[] tId = gson.fromJson(tIds,Integer[].class);
        //调用方法    执行批量删除
        teacherMapper.batchDelete(tId);
    }

    @Override
    public Teacher queryByPassWord(Teacher teacher) {
        return teacherMapper.queryByPassWord(teacher);
    }

    @Override
    public void updatePassWord(Teacher teacher) {
        teacherMapper.updatePassWord(teacher);
    }


}
