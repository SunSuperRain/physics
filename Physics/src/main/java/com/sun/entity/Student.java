package com.sun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 超雨
 * @create 2020--10--02--19:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String stuNo;
    private String userName;
    private String passWord;
    private String studentName;
    private Integer age;
    private String sex;
    private Integer classNo;
    private Integer tId;
    private String permission;
    private Integer card;
    private String Phone;
    private String Email;
}
