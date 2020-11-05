package com.sun.service;

import com.sun.entity.Trial;
import com.sun.entity.Vision;
import com.sun.utils.PageUtils;

import java.util.List;

/**
 * @author 超雨
 * @create 2020--10--08--16:37
 */
public interface VisionService {
    //获取前四个实验信息
    List<Vision> getFirstVision();

    //获取接着四个的实验信息
    List<Vision> getLastVision();

    //查询所有的视频信息  附带分页  模糊查询
    PageUtils<Vision> getAllVision(Integer pageNO, String visionName);

    //根据编号搜索视频信息
    Vision getVisionByID(Integer visionID);

    //获取所有的视频信息
    List<Vision> getAllVisions(Vision vision);

    //增加一个教学视频的信息
    void addVision(Vision vision);
}
