package com.sun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sun
 * @version 1.0
 * @date 2020/10/26 17:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    private Integer boardID;
    private String content;
    private Integer tId;
    private String teacherName;
    private Integer aId;
    private String adminName;
    private String submitTime;
}
