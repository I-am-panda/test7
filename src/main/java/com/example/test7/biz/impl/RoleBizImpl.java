package com.example.test7.biz.impl;

import com.example.test7.biz.RoleBiz;
import com.example.test7.dao.RoleMapper;
import com.example.test7.entity.Role;
import com.example.test7.util.MyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleBizImpl implements RoleBiz {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> selectAllRole() {
        return roleMapper.selectAllRole();
    }

    @Override
    public int insertSelective(Role roleInfo) {
        MyConstants myConstants =new MyConstants();
        roleInfo.setCreateTime(myConstants.getDate());
        return roleMapper.insert(roleInfo);
    }

    @Override
    public int updateByPrimaryKeySelective(Role roleInfo) {
        MyConstants myConstants =new MyConstants();
        roleInfo.setUpdateTime(myConstants.getDate());
        return roleMapper.updateByPrimaryKeySelective(roleInfo);
    }

    @Override
    public int delRoleByID(List<String> list) {
        return roleMapper.delUserByID(list);
    }
}
