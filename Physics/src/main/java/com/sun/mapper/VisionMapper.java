package com.sun.mapper;

import com.sun.applet2.preloader.event.InitEvent;
import com.sun.entity.Trial;
import com.sun.entity.Vision;
import com.sun.org.apache.xml.internal.serialize.LineSeparator;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 超雨
 * @create 2020--10--08--16:33
 */
public interface VisionMapper {
    //获取前四个实验信息
    List<Vision> getFirstVision();

    //获取接着四个的实验信息
    List<Vision> getLastVision();

    //获取所有的视频信息 附带模糊  分页查询
    List<Vision> getAllVision(@Param("visionName") String visionName, @Param("startPos") Integer startPos, @Param("endPos") Integer endPos);

    //模糊查询获取所有实验信息
    Integer getAllCount(@Param("visionName") String visionName);

    //根据编号搜索视频信息
    Vision getVisionByID(@Param("visionID") Integer visionID);

    //根据id修改教学视频的信息
    void updateVision(Vision vision);

    //根据id删除一个视频信息
    void deleteVision(@Param("visionID") Integer visionID);

    //增加一个教学视频的信息
    void addVision(Vision vision);

    //获取所有的视频信息
    List<Vision> getAllVisions(Vision vision);
}
