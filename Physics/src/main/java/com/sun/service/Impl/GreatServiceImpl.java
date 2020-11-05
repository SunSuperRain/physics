package com.sun.service.Impl;

import com.sun.entity.Great;
import com.sun.mapper.GreatMapper;
import com.sun.service.GreatService;

/**
 * @author 超雨
 * @create 2020--10--07--17:29
 */
public class GreatServiceImpl implements GreatService {

    //service层调用dao层方法
    public GreatMapper greatMapper;

    public void setGreatMapper(GreatMapper greatMapper) {
        this.greatMapper = greatMapper;
    }

    public Great queryByNo(Integer essayID, String stuNo) {
        return greatMapper.queryByNo(essayID,stuNo);
    }

    public void addGreat(Integer essayID, String stuNo) {
        greatMapper.addGreat(essayID,stuNo);
    }

    public void deleteGreat(Integer essayID, String stuNo) {
        greatMapper.deleteGreat(essayID, stuNo);
    }
}
