package com.example.test7.controller;

import com.alibaba.fastjson.JSON;
import com.example.test7.biz.MenuBiz;
import com.example.test7.entity.LayUiTable;
import com.example.test7.entity.Menu;
import com.example.test7.entity.ResultEntity;
import com.example.test7.util.MyConstants;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuBiz menuBiz;
    @RequestMapping("/toShowMenu")
    public String toShowMenu() {
        return "menu/showMenu";
    }

    //	根据关键字查询数据并且分页显示
    @RequestMapping("/showMenu")      //接口路径
    @ResponseBody
    public LayUiTable showmenuInfo(
            @RequestParam(value="page", defaultValue="1") Integer page,    //第几页
            @RequestParam(value="limit", defaultValue="1000") Integer limit,   //一页几条数据
            @RequestParam(value="visible", defaultValue="-1") Integer visible,  //查询条件菜单状态，如果没有就不用传输该数据
            @RequestParam(value="menuName", defaultValue="") String menuName    //查询条件菜单名字，如果没有就不用传输该数据
    ) {
        //开始查询
        PageInfo<Menu> pageInfo = menuBiz.showMenuInfo(page, limit,visible,menuName);
        LayUiTable layUiTable = new LayUiTable();
        layUiTable.setCode(0);
        layUiTable.setMsg("返回消息");
        //设置分页之后的返回值
        layUiTable.setCount(pageInfo.getTotal());
        layUiTable.setData(pageInfo.getList());
        return layUiTable;
    }

    @RequestMapping("/addMenu")
    @ResponseBody
    public ResultEntity<Menu> addMenu(Menu menu)
    {
        menuBiz.addMenu(menu);
        return ResultEntity.successWithoutData();
    }


    @RequestMapping("/deleteMenus")
    @ResponseBody
    public Object delUser( @RequestParam(value = "menus") String  menus){
        //将json字符串转换成list对象
        List<String> list= (List<String>) JSON.parse(menus);
        int i = menuBiz.delMenusByID(list);
        if(i>0){
            return ResultEntity.successWithoutData();
        }else {
            return ResultEntity.failed(MyConstants.delFailMsg);
        }
    }
    @RequestMapping("/updateMenu")
    @ResponseBody
    public ResultEntity<Menu> updateMenu(Menu menu)
    {
        menuBiz.updateMenu(menu);
        return ResultEntity.successWithoutData();
    }
}

