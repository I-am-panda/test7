package com.example.test7.dao;

import com.example.test7.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MenuMapper {
    int deleteByPrimaryKey(Integer menuId);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> selectAllMenu();

    List<Menu> selectMenuInfo(Integer visible, String menuName);

    List<Menu>  selectAllMenuByName(String loginName);

    Menu selectMenuNameById(int fatherId);

    int delMenusByID(List<String> list);
}