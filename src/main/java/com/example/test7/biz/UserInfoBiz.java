package com.example.test7.biz;

import com.example.test7.entity.MyUserInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserInfoBiz {

    PageInfo<MyUserInfo> selectAllUser(int page, int limit);
    MyUserInfo selectUserByUsername(String username);
    int insertSelective(MyUserInfo record);
    int delUserByID(List<String> ids);
    int updateByPrimaryKeySelective(MyUserInfo record);
}
