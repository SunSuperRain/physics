package com.sun.service;

import com.sun.entity.Essay;
import com.sun.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 超雨
 * @create 2020--10--06--20:32
 */
public interface EssayService {
    //根据分页  模糊 查询  获取文章信息
    PageUtils<Essay> queryByPaging(Integer pageNo,String skill);

    //增加一个话题的方法
    Integer addEssay(Essay essay);

    //根据文章编号获取文章内容
    Essay getByEssayID(@Param("essayID") Integer essayID);

    //更新用户点赞数目
    void updateFans(String stuNo, Integer essayID);

    //更新文章浏览记录
    void updateRecord(Integer essayID);

    //查询所有的文章信息
    List<Essay> getAllEssay(Essay essay);

    //根据id删除文章信息
    void deleteById(Integer essayID);
}
