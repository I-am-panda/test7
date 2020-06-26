package com.example.test7.dao;

import com.example.test7.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectUserByUsername(String username);

    int updatepasswordByID(User record);

    int delUserByID( @Param("ids") List<String> ids);

    List<User> selectAllUser();

    List<User> selectUserInfo(String loginName,String phonenumber,String sex);
}