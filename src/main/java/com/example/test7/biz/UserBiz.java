package com.example.test7.biz;


import com.example.test7.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserBiz {

    PageInfo<User> selectAllUser(int page, int limit);
    User selectUserByUsername(String username);
    int insertSelective(User record);
    int delUserByID(List<String> ids);
    int updatepasswordByID(User record);
    int updateByPrimaryKeySelective(User record);
    List<User> showUserInfo(String loginName,String phonenumber,String status );
}
