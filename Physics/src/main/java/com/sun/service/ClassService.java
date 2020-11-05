package com.sun.service;

import com.sun.entity.ClassRoom;
import com.sun.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 超雨
 * @create 2020--10--14--12:09
 */
public interface ClassService {
    //查询所有的班级   附带分页  模糊查询
    PageUtils<ClassRoom> queryByPaging(String className,Integer pageNo);

    //查询数量
    //查询所有的班级数量   附带分页查询
    Integer queryTotal(String className);

    //查询所有班级
    List<ClassRoom> getAllClassRoom(ClassRoom classRoom);

    //根据班级号获取班级信息
    ClassRoom queryByClassNo(@Param("classNo") Integer classNo);

    //更新班级信息
    void updateById(Integer classNo);
}
