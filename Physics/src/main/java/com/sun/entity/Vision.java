package com.sun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 超雨
 * @create 2020--10--08--16:03
 */
//建立视频类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vision {
    private Integer visionID;
    private String image;
    private String introduction;
    private String keyWord;
    private Integer record;
    private String visionLink;
    private String visionName;
}
