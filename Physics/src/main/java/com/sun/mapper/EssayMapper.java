package com.sun.mapper;

import com.sun.entity.Essay;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 超雨
 * @create 2020--10--06--20:21
 */
public interface EssayMapper {
    //模糊查询获取关键词的数目
    Integer queryCountBySkill(@Param("skill") String skill);

    //模糊查询  分页  获取文章信息
    List<Essay> queryByPaging(@Param("skill") String skill,@Param("startPos") Integer startPos,@Param("endPos") Integer endPos);

    //增加一个话题的方法
    Integer addEssay(Essay essay);

    //根据文章编号获取文章内容
    Essay getByEssayID(@Param("essayID") Integer essayID);

    //更新文章点赞信息
    void updateEssayFan(@Param("essayID") Integer essayID,@Param("fans") Integer fans);

    //由文章编号获取点赞信息
    Integer getFanByEssayID(@Param("essayID") Integer essayID);

    //更新文章浏览数据
    void updateRecord(@Param("essayID") Integer essayID,@Param("record") Integer record);

    //根据id删除文章信息
    void deleteById(@Param("essayID") Integer essayID);

    //查询所有的文章信息
    List<Essay> getAllEssay(Essay essay);
}
