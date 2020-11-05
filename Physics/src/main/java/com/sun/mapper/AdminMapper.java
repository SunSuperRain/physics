package com.sun.mapper;

import com.sun.entity.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 超雨
 * @create 2020--10--12--21:17
 */
//处理管理员的dao层
public interface AdminMapper {
    //根据用户名和密码    判断管理员是否存在
    Admin login(@Param("userName") String userName,@Param("passWord") String passWord);

    Admin getByID(@Param("aId") Integer aId);

    Integer updatePassWord(Admin admin);

    //根据当前密码和id查询是否存在此用户
    Admin queryByPassWord(Admin admin);

    void updateAdmin(Admin admin);

    //获取管理员职责
    List<Map> getRole(@Param("card") Integer card);

    //获取所有管理员信息
    List<Admin> getAllAdmin();
}
