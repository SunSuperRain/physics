package com.sun.mapper;

import com.sun.entity.ClassRoom;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 超雨
 * @create 2020--10--14--12:02
 */
//处理班级类的接口
public interface ClassMapper {
    //查询所有的班级   附带分页  模糊查询
    List<ClassRoom> queryByPaging(@Param("className") String className, @Param("pageNo") Integer pageNo, @Param("startPos") Integer startPos, @Param("endPos") Integer endPos);

    //查询所有的班级数量   附带分页查询
    Integer queryTotal(@Param("className") String className);

    //更新班级信息
    void updateById(ClassRoom classRoom);

    //删除课程信息
    void deleteById(@Param("classNo") Integer classNo);

    //新增一个班级
    void addClassRoom(ClassRoom classRoom);

    //查询所有班级
    List<ClassRoom> getAllClassRoom(ClassRoom classRoom);

    //根据班级号获取班级信息
    ClassRoom queryByClassNo(@Param("classNo") Integer classNo);
}
