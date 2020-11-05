package com.sun.service;

import com.sun.entity.Teacher;
import com.sun.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 超雨
 * @create 2020--10--13--8:49
 */
public interface TeacherService {
    Teacher login(String userName,String passWord);

    //查询教师信息  附带分页模糊查询
    PageUtils<Teacher> queryByPaging(Integer pageNo,Teacher teacher);

    //查询教师数量  附带模糊查询
    Integer queryTotal(Teacher teacher);

    //根据教师ID修改教师信息
    void updateById(Teacher teacher);

    //根据教师ID删除教师信息
    void deleteById(Integer tId);

    //增加教师信息
    void addTeacher(Teacher teacher);

    //查询所有教师的信息
    List<Teacher> queryAllTeacher(Teacher teacher);

    //根据教师ID查询教师信息
    Teacher queryById(Integer tId);

    //批量删除教师信息
    void batchDelete(String tIds);

    //修改密码信息
    Teacher queryByPassWord(Teacher teacher);

    //修改密码
    void updatePassWord(Teacher teacher);

}
