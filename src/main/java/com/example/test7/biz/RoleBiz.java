package com.example.test7.biz;

import com.example.test7.entity.Role;

import java.util.List;

public interface  RoleBiz {
   List<Role> selectAllRole();
   int insertSelective(Role roleInfo);
   int updateByPrimaryKeySelective(Role record);
   int delRoleByID(List<String> list);
}
