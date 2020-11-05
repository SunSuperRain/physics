package com.sun.service.Impl;

import com.sun.entity.Essay;

import com.sun.mapper.EssayMapper;
import com.sun.mapper.GreatMapper;
import com.sun.service.EssayService;

import com.sun.utils.PageUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * @author 超雨
 * @create 2020--10--06--20:34
 */
public class EssayServiceImpl implements EssayService {

    //service层调用dao层方法
    EssayMapper essayMapper;

    @Autowired
    GreatMapper greatMapper;


    public void setEssayMapper(EssayMapper essayMapper) {
        this.essayMapper = essayMapper;
    }

    //进行分页查询的逻辑处理
    public PageUtils<Essay> queryByPaging(Integer pageNo, String skill) {
        //获取分页对象
        PageUtils<Essay> pageUtils = new PageUtils<Essay>(pageNo);
        pageUtils.setList(essayMapper.queryByPaging(skill,(pageUtils.getPageNo() - 1)*pageUtils.getPageSize(),pageUtils.getPageSize()));
        pageUtils.setTotalCount(essayMapper.queryCountBySkill(skill));
        return pageUtils;
    }

    public Integer addEssay(Essay essay) {
        return essayMapper.addEssay(essay);
    }

    //根据文章编号获取文章内容
    public Essay getByEssayID(@Param("essayID") Integer essayID){
        return essayMapper.getByEssayID(essayID);
    }

    //更新用户的点赞信息
    public void updateFans(String stuNo, Integer essayID) {

        if(null != greatMapper.queryByNo(essayID,stuNo)){
            //用户已经点赞    删除用户信息  将文章表中点赞数减一
            greatMapper.deleteGreat(essayID,stuNo);
            //取出当前点赞数目
            Integer fan = essayMapper.getFanByEssayID(essayID);
            //赞数自减
            essayMapper.updateEssayFan(essayID,--fan);
        }else {
            //用户还未点赞    添加用户信息  将文章表中点赞数加一
            greatMapper.addGreat(essayID,stuNo);
            Integer fan = essayMapper.getFanByEssayID(essayID);
            //文章点赞数目自增
            essayMapper.updateEssayFan(essayID,++fan);
        }

    }

    //更新文章浏览记录  进行逻辑代码处理
    //更新文章浏览记录
    public void updateRecord(Integer essayID){
        //获取文章初始浏览数
        int record = essayMapper.getByEssayID(essayID).getRecord();
        //更新文章浏览记录
        essayMapper.updateRecord(essayID,++record);
    }

    @Override
    public List<Essay> getAllEssay(Essay essay) {
        return essayMapper.getAllEssay(essay);
    }

    @Override
    public void deleteById(Integer essayID) {
        essayMapper.deleteById(essayID);
    }

}
