package com.sun.mapper;

import com.sun.entity.Great;
import org.apache.ibatis.annotations.Param;

/**
 * @author 超雨
 * @create 2020--10--07--17:23
 */
public interface GreatMapper {
    //根据文章号   学号获取  数据库信息
    Great queryByNo(@Param("essayID") Integer essayID,@Param("stuNo") String stuNo);

    //添加点赞信息
    void addGreat(@Param("essayID") Integer essayID,@Param("stuNo") String stuNo);

    //删除点赞记录
    void deleteGreat(@Param("essayID") Integer essayID,@Param("stuNo") String stuNo);
}
