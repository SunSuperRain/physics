package com.sun.mapper;

import com.sun.entity.Trial;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 超雨
 * @create 2020--10--04--20:04
 */
//处理有关实验的接口
public interface TrialMapper {
    //获取所有的实验
    List<Trial> getAllTrial(@Param("trialName") String trialName,@Param("startPos") Integer startPos,@Param("endPos") Integer endPos);

    //获取前四个实验
    List<Trial> getFirstTrial();

    //获取后四个实验
    List<Trial> getLastTrial();

    //根据编号获取实验信息
    Trial getByNo(@Param("trialNo") Integer trialNo);

    //根据标号将实验次数加一
    Integer addCountByNo(@Param("trialNo") Integer trialNo,@Param("counts") Integer counts);

    //获取实验的总数
    Integer queryTotal();

    //根据实验关键字进行模糊查询
    List<Trial> queryByName(@Param("trialName") String trialName);

    //模糊查询获取实验总数
    Integer getCountByTrialName(@Param("trialName") String trialName);

    //根据实验ID更新一个实验信息
    void updateTrial(Trial trial);

    //根据实验ID删除一个实验信息
    void deleteTrial(@Param("trialNo") Integer trialNo);

    //增加一个实验信息
    void addTrial(Trial trial);

    //查询所有的实验信息
    List<Trial> getAllTrials(Trial trial);

    //批量删除实验i信息
    void batchDelete(@Param("trialNos") Integer[] trialNos);

    //获取所有的实验信息
    Integer getAllCounts();

}
