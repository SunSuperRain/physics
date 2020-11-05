package com.sun.mapper;

import com.sun.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Sun
 * @version 1.0
 * @date 2020/10/22 17:36
 */
public interface RoleMapper {
    //根kind的信息  获取用户的职责
    List<Role> getRoles(@Param("card") Integer card);
}
