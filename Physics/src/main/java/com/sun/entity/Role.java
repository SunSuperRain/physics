package com.sun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sun
 * @version 1.0
 * @date 2020/10/22 17:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private Integer roleID;
    private String permissionName;
    private String permissionLink;
    private Integer kind;
}
