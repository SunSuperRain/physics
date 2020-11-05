package com.sun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 超雨
 * @create 2020--10--02--19:51
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Trial {
    private Integer trialNo;
    private String image;
    private String introduction;
    private String expression;
    private String skill;
    private Integer tId;
    private String figure;
    private String figureName;
    private String figureIntroduction;
    private String trialName;
    private String keyWord;
    private Integer counts;
    private String trialLink;
}
