package com.sun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 超雨
 * @create 2020--10--07--11:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
    private Integer replyID;
    private Integer essayID;
    private String stuNo;
    private String studentName;
    private String content;
    private String replyTime;
    private Integer fans;
}
