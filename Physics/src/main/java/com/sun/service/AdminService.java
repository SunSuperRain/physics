package com.sun.service;

import com.sun.entity.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 超雨
 * @create 2020--10--13--7:52
 */
public interface AdminService {
    //根据用户名和密码    判断管理员是否存在
    Admin login(String userName,String passWord);

    Admin getByID(Integer aId);

    Integer updatePassWord(Admin admin);

    //根据当前密码和id查询是否存在此用户
    Admin queryByPassWord(Admin admin);

    void updateAdmin(Admin admin);

    //获取管理员职责
    List<Map> getRole(Integer card);

    //获取所有管理员信息
    List<Admin> getAllAdmin();
}
