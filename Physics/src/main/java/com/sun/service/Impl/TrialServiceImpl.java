package com.sun.service.Impl;

import com.google.gson.Gson;
import com.sun.entity.Trial;
import com.sun.mapper.TrialMapper;
import com.sun.service.TrialService;
import com.sun.utils.PageUtils;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;

/**
 * @author 超雨
 * @create 2020--10--04--20:07
 */
public class TrialServiceImpl implements TrialService {

    //service层dao层方法
    private TrialMapper trialMapper;

    public void setTrialMapper(TrialMapper trialMapper) {
        this.trialMapper = trialMapper;
    }

/*    public List<Trial> getAllTrial() {
        return trialMapper.getAllTrial();
    }*/

    public List<Trial> getFirstTrial() {
        return trialMapper.getFirstTrial();
    }

    public List<Trial> getLastTrial() {
        return trialMapper.getLastTrial();
    }

    public Trial getByNo(Integer trialNo) {
        return trialMapper.getByNo(trialNo);
    }

    public Integer addCountByNo(Integer trialNo, Integer counts) {
        return trialMapper.addCountByNo(trialNo,counts);
    }

    public Integer queryTotal() {
        return trialMapper.queryTotal();
    }

    //根据实验关键字进行模糊查询
    public List<Trial> queryByName(String Name){
        return trialMapper.queryByName(Name);
    }

    /*
    service层处理逻辑代码
    处理后端与前端的分页查询功能
    * */
    //根据分页  模糊查询获取实验信息
     public PageUtils<Trial> queryByPaging(Integer pageNo, String trialName){
         //逻辑处理  返回集合给 controller层
         //获取分页对象
         PageUtils<Trial> trialPageUtils = new PageUtils<Trial>(pageNo);
         trialPageUtils.setList(trialMapper.getAllTrial(trialName,(trialPageUtils.getPageNo() - 1)*trialPageUtils.getPageSize(),trialPageUtils.getPageSize()));
         trialPageUtils.setTotalCount(trialMapper.getCountByTrialName(trialName));
        return trialPageUtils;
    }

    @Override
    public List<Trial> getAllTrials(Trial trial) {
        return trialMapper.getAllTrials(trial);
    }

    @Override
    public void deleteTrial(Integer trialNo) {
        trialMapper.deleteTrial(trialNo);
    }

    @Override
    public void addTrial(Trial trial) {
        trialMapper.addTrial(trial);
    }

    @Override
    public void batchDelete(String arrayStr) {
         //转换为数组形式
        Gson gson = new Gson();
        Integer[] trialNos = gson.fromJson(arrayStr,Integer[].class);
        trialMapper.batchDelete(trialNos);
    }

    @Override
    public Integer getAllCounts() {
        return trialMapper.getAllCounts();
    }


}
