package com.example.test7.controller;

import com.example.test7.biz.DeptBiz;
import com.example.test7.entity.*;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptBiz deptBizImpl;
    @RequestMapping("/toShowDept")
    public String  toShowDept(){
        return "dept/showDept";
    }

    @RequestMapping("/showDept2")      //接口路径
    @ResponseBody
    public List<Tree> showmenuInfo(){
        List<Tree> Tree= deptBizImpl.selectAll();
        return Tree;
   }

    //	根据关键字查询数据并且分页显示
    @RequestMapping("/showDept")      //接口路径
    @ResponseBody
    public LayUiTable showmenuInfo(
            @RequestParam(value="page", defaultValue="1") Integer page,    //第几页
            @RequestParam(value="limit", defaultValue="1000") Integer limit,   //一页几条数据
            @RequestParam(value="visible", defaultValue="-1") Integer visible,  //查询条件菜单状态，如果没有就不用传输该数据
            @RequestParam(value="deptName", defaultValue="") String menuName    //查询条件菜单名字，如果没有就不用传输该数据
    ) {
        //开始查询
        PageInfo<Dept> pageInfo = deptBizImpl.showDeptInfo(page, limit,visible,menuName);
        LayUiTable layUiTable = new LayUiTable();
        layUiTable.setCode(0);
        layUiTable.setMsg("返回消息");
        //设置分页之后的返回值
        layUiTable.setCount(pageInfo.getTotal());
        layUiTable.setData(pageInfo.getList());
        return layUiTable;
    }
}
