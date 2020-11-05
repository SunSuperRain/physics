package com.sun.service;

import com.sun.entity.Reply;
import com.sun.entity.Trial;
import com.sun.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 超雨
 * @create 2020--10--04--20:06
 */
public interface TrialService {
    //获取所有的实验
/*    List<Trial> getAllTrial();*/

    //获取前四个实验
    List<Trial> getFirstTrial();

    //获取后四个实验
    List<Trial> getLastTrial();

    //根据编号获取实验信息
    Trial getByNo(Integer trialNo);

    //根据标号将实验次数加一
    Integer addCountByNo(Integer trialNo,Integer counts);

    //获取实验的总数
    Integer queryTotal();

    //根据实验关键字进行模糊查询
    List<Trial> queryByName(String Name);

    //根据分页  模糊查询获取实验信息
    PageUtils<Trial> queryByPaging(Integer pageNo, String trialName);

    //查询所有的实验信息
    List<Trial> getAllTrials(Trial trial);

    //根据实验ID删除一个实验信息
    void deleteTrial(Integer trialNo);

    //增加一个实验信息
    void addTrial(Trial trial);

    //批量删除实验信息
    void batchDelete(String arrayStr);

    //获取所有的实验信息
    Integer getAllCounts();

}
