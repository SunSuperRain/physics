package com.sun.mapper;

import com.sun.entity.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @author 超雨
 * @create 2020--10--13--8:47
 */
public interface TeacherMapper {
    Teacher login(@Param("userName") String userName,@Param("passWord") String passWord);

    //查询所有教师信息  附带分页  模糊查询
    List<Teacher> queryByPaging(@Param("teacherName") String teacherName,@Param("phone") String phone,@Param("startPos") Integer startPos,@Param("endPos") Integer endPos);

    //查询教师数量  附带模糊查询
    Integer queryTotal(@Param("teacherName") String teacherName,@Param("phone") String phone);

    //查询教师的业务功能
    List<HashMap> getRole(@Param("card") Integer card);

    //根据教师ID修改教师信息
    void updateById(Teacher teacher);

    //根据教师ID删除教师信息
    void deleteById(@Param("tId") Integer tId);

    //增加教师信息
    void addTeacher(Teacher teacher);

    //查询所有教师的信息
    List<Teacher> queryAllTeacher(Teacher teacher);

    //获取左侧菜单栏内容
    List getAllRole();

    //根据教师ID查询教师信息
    Teacher queryById(@Param("tId") Integer tId);

    //批量删除教师信息
    void batchDelete(@Param("tIds") Integer[] tIds);

    //修改密码信息
    Teacher queryByPassWord(Teacher teacher);

    //修改密码
    void updatePassWord(Teacher teacher);

}
