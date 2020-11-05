package com.sun.service;

import com.sun.entity.Great;

import org.apache.ibatis.annotations.Param;

/**
 * @author 超雨
 * @create 2020--10--07--17:27
 */
public interface GreatService {
    //根据文章号   学号获取  数据库信息
    Great queryByNo(Integer essayID,String stuNo);

    //添加点赞信息
    void addGreat(Integer essayID,@Param("stuNo") String stuNo);

    //删除点赞记录
    void deleteGreat(Integer essayID,@Param("stuNo") String stuNo);
}
