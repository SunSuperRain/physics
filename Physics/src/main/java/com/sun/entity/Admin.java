package com.sun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 超雨
 * @create 2020--10--02--19:46
 */
//建立管理员实体类
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
public class Admin {
    private Integer aId;
    private String userName;
    private String passWord;
    private String adminName;
    private String sex;
    private Integer age;
    private String permission;
    private Integer card;
    private String phone;
    private String email;
}
