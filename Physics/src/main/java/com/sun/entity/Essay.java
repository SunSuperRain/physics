package com.sun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 超雨
 * @create 2020--10--06--16:58
 */
//建立文章类信息
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Essay {
    private Integer essayID;
    private String essayName;
    private String content;
    private Integer fans;
    private Integer record;
    private String stuNo;
    private String studentName;
    private String essayTime;
    private String keyWord;
    private String skill;
}
