package com.sun.service.Impl;

import com.sun.entity.ClassRoom;
import com.sun.mapper.ClassMapper;
import com.sun.service.ClassService;
import com.sun.utils.PageUtils;

import java.util.List;

/**
 * @author 超雨
 * @create 2020--10--14--12:15
 */
public class ClassServiceImpl implements ClassService {

    //service层调用dao层方法
    ClassMapper classMapper;

    public void setClassMapper(ClassMapper classMapper) {
        this.classMapper = classMapper;
    }

    //service层处理业务逻辑
    public PageUtils<ClassRoom> queryByPaging(String className,Integer pageNo) {
        PageUtils<ClassRoom> pageUtils = new PageUtils<ClassRoom>(pageNo);
        pageUtils.setList(classMapper.queryByPaging(className,pageUtils.getPageNo(),(pageUtils.getPageNo()-1)*pageUtils.getPageSize(),pageUtils.getPageSize()));
        pageUtils.setTotalCount(classMapper.queryTotal(className));
        return pageUtils;
    }

    public Integer queryTotal(String className) {
        return classMapper.queryTotal(className);
    }

    @Override
    public List<ClassRoom> getAllClassRoom(ClassRoom classRoom) {
        return classMapper.getAllClassRoom(classRoom);
    }

    @Override
    public ClassRoom queryByClassNo(Integer classNo) {
        return classMapper.queryByClassNo(classNo);
    }

    //进行业务的处理
    @Override
    public void updateById(Integer classNo) {
        ClassRoom classRoom = new ClassRoom();
        classRoom.setClassNo(classNo);
        Integer currentTotal = classMapper.queryByClassNo(classRoom.getClassNo()).getCurrentTotal();
        classRoom.setCurrentTotal(currentTotal++);
        classMapper.updateById(classRoom);
    }
}
