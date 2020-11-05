package com.sun.service.Impl;

import com.sun.entity.Role;
import com.sun.mapper.RoleMapper;
import com.sun.service.RoleService;

import java.util.List;

/**
 * @author Sun
 * @version 1.0
 * @date 2020/10/22 17:41
 */
public class RoleServiceImpl implements RoleService {
    //service层调用dao层
    RoleMapper roleMapper;

    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public List<Role> getRoles(Integer card) {
        return roleMapper.getRoles(card);
    }
}
