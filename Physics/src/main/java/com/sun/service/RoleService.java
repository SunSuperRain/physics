package com.sun.service;

import com.sun.entity.Role;
import com.sun.mapper.RoleMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Sun
 * @version 1.0
 * @date 2020/10/22 17:39
 */
public interface RoleService {
    //根kind的信息  获取用户的职责
    List<Role> getRoles(Integer card);
}
