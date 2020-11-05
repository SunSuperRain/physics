package com.sun.service.Impl;

import com.sun.entity.Trial;
import com.sun.entity.Vision;
import com.sun.mapper.VisionMapper;
import com.sun.service.VisionService;
import com.sun.utils.PageUtils;

import java.util.List;

/**
 * @author 超雨
 * @create 2020--10--08--16:37
 */
public class VisionServiceImpl implements VisionService {

    //service层调用dao层
    VisionMapper visionMapper;

    public void setVisionMapper(VisionMapper visionMapper) {
        this.visionMapper = visionMapper;
    }

    public List<Vision> getFirstVision() {
        return visionMapper.getFirstVision();
    }

    public List<Vision> getLastVision() {
        return visionMapper.getLastVision();
    }

    public PageUtils<Vision> getAllVision(Integer pageNO, String visionName) {
        //获取分页对象
        PageUtils<Vision> pageUtils = new PageUtils<Vision>(pageNO);
        pageUtils.setList(visionMapper.getAllVision(visionName,(pageUtils.getPageNo()-1)*pageUtils.getPageSize(),pageUtils.getPageSize()));
        pageUtils.setTotalCount(visionMapper.getAllCount(visionName));
        return pageUtils;
    }

    public Vision getVisionByID(Integer visionID) {
        return visionMapper.getVisionByID(visionID);
    }

    //获取所有的视频信息
    public List<Vision> getAllVisions(Vision vision){
        return visionMapper.getAllVisions(vision);
    }

    @Override
    public void addVision(Vision vision) {
        visionMapper.addVision(vision);
    }


}
