package com.example.test7.biz;

import com.example.test7.entity.Menu;
import com.example.test7.entity.LayUiTree;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface MenuBiz {

    //根据用户登录名查询对应的所有菜单
    List<LayUiTree>  selectAllMenuByName(String loginName);

    PageInfo<Menu> showMenuInfo(Integer page, Integer limit, Integer visible, String menuName);

    void addMenu(Menu menu);

    int deleteMenu(Integer menuID);

    void updateMenu(Menu menu);

    int delMenusByID(List<String> list);
}