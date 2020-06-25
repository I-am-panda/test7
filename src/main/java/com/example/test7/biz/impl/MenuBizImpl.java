package com.example.test7.biz.impl;

import com.example.test7.biz.MenuBiz;
import com.example.test7.dao.MenuMapper;
import com.example.test7.entity.Menu;
import com.example.test7.entity.LayUiTree;
import com.example.test7.util.TreeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MenuBizImpl implements MenuBiz {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<LayUiTree> selectAllMenuByName(String loginName) {
        //查询所有的菜单
        List<Menu> menus = menuMapper.selectAllMenuByName(loginName);
        //并组装成tree格式的
        return TreeUtils.getChildPerms(menus, 0);
    }

    @Override
    public PageInfo<Menu> showMenuInfo(Integer page, Integer limit, Integer visible, String menuName) {
        //开始分页,第一个参数是当前第几页，第二个参数是一页显示多少行
        PageHelper.startPage(page,limit);
        int fatherId;
        Menu menu=null;
        List<Menu> menuInfo = menuMapper.selectMenuInfo(visible,menuName);

        for(Menu m : menuInfo)
        {
            fatherId=m.getParentId();
            menu=menuMapper.selectMenuNameById(fatherId);
            if(menu!=null)
                m.setFatherName(menu.getMenuName());
        }
        //结束分页,pageInfo封装了分页之后所有数据
        PageInfo<Menu> pageInfo = new PageInfo<Menu>(menuInfo);
        return pageInfo;
    }

    @Override
    public void addMenu(Menu menu) {
        // TODO Auto-generated method stub

        menuMapper.insert(menu);
    }

    @Override
    public int deleteMenu(Integer menuID) {
        // TODO Auto-generated method stub
        menuMapper.deleteByPrimaryKey(menuID);
        return 0;
    }

    @Override
    public int delMenusByID(List<String> list) {
        // TODO Auto-generated method stub
        return menuMapper.delMenusByID(list);
    }

    @Override
    public void updateMenu(Menu menu) {
        // TODO Auto-generated method stub
        menuMapper.updateByPrimaryKey(menu);
    }
}
