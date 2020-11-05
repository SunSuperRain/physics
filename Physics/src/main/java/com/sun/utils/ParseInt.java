package com.sun.utils;

/**
 * @author 超雨
 * @create 2020--10--06--11:38
 */
//将字符转化为数字   若为null  转化为空
public class ParseInt {
    public static Integer getInteger(String name){
        if(null != name && !"".equals(name)){
            return Integer.parseInt(name);
        }else {
            return null;
        }
    }
}
