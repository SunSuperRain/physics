package com.sun.service.Impl;

import com.sun.entity.Admin;
import com.sun.mapper.AdminMapper;
import com.sun.service.AdminService;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;

/**
 * @author 超雨
 * @create 2020--10--13--7:53
 */
public class AdminServiceImpl implements AdminService {

    //service层调用dao层方法

    AdminMapper adminMapper;

    public void setAdminMapper(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    public Admin login(String userName, String passWord) {
        return adminMapper.login(userName,passWord);
    }

    public Admin getByID(Integer aId) {
        return adminMapper.getByID(aId);
    }

    public Integer updatePassWord(Admin admin){
        return adminMapper.updatePassWord(admin);
    }

    public Admin queryByPassWord(Admin admin) {
        return adminMapper.queryByPassWord(admin);
    }

    public void updateAdmin(Admin admin) {
        adminMapper.updateAdmin(admin);
    }

    public List<Map> getRole(Integer card) {
        return adminMapper.getRole(card);
    }

    @Override
    public List<Admin> getAllAdmin() {
        return adminMapper.getAllAdmin();
    }
}
