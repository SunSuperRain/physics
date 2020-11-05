package com.sun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 超雨
 * @create 2020--10--12--20:42
 */
//班级类
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
public class ClassRoom {
    private Integer classNo;
    private String className;
    private Integer currentTotal;
    private Integer maxTotal;
}
