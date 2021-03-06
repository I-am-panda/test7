package com.example.test7.controller;

import com.example.test7.biz.UserBiz;
import com.example.test7.entity.LayUiTable;
import com.example.test7.entity.User;
import com.example.test7.util.MyConstants;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
//此处的RequestMapping是窄化请求
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserBiz userBizImpl;

    @RequestMapping("/toShowUser")
    public String toShowUserLayui() {
        return "user/showUser";
    }

    @RequestMapping("toShowMain")
    public String toShowMainLayui() {
        return "main";
    }

    @RequestMapping("/showUser")
    @ResponseBody
    public LayUiTable showUserLayui(int page, int limit) {
        //开始查询
        PageInfo<User> pageInfo = userBizImpl.selectAllUser(page, limit);
        LayUiTable layUiTable = new LayUiTable();
        layUiTable.setCode(0);
        layUiTable.setMsg("返回消息");
        //设置分页之后的返回值
        layUiTable.setCount(pageInfo.getTotal());
        layUiTable.setData(pageInfo.getList());
        return layUiTable;
    }

    /**
     * 添加用户信息
     * @param userInfo
     * @return
     */
    @RequestMapping("/saveUser")
    @ResponseBody
    public Object saveUser(User userInfo){
        int i = userBizImpl.insertSelective(userInfo);
        Map map= new HashMap<>();
        if(i>0){
            map.put("code",MyConstants.successCode);
            map.put("message",MyConstants.saveSuccessMsg);
        }else {
            map.put("code",MyConstants.failCode);
            map.put("message",MyConstants.saveFailMsg);
        }
        return map;
    }

    /**
     * 修改用户信息
     * @param userInfo
     * @return
     */
    @RequestMapping("/editUser")
    @ResponseBody
    public Object editUser(User userInfo){
        int i = userBizImpl.updateByPrimaryKeySelective(userInfo);
        Map map= new HashMap<>();
        if(i>0){
            map.put("code",MyConstants.successCode);
            map.put("message",MyConstants.editSuccessMsg);
        }else {
            map.put("code",MyConstants.failCode);
            map.put("message",MyConstants.editFailMsg);
        }
        return map;
    }

    /**
     * 修改密码
     * @param userInfo
     * @return
     */
    @RequestMapping("/editPassword")
    @ResponseBody
    public Object editPassword(User userInfo){
        int i = userBizImpl.updatepasswordByID(userInfo);
        Map map= new HashMap<>();
        if(i>0){
            map.put("code",MyConstants.successCode);
            map.put("message",MyConstants.editSuccessMsg);
        }else {
            map.put("code",MyConstants.failCode);
            map.put("message",MyConstants.editFailMsg);
        }
        return map;
    }

    /*	根据关键字查询数据*/
    @RequestMapping("/searchUser")
    @ResponseBody
    public LayUiTable showUserInfo(
            @RequestParam(value="loginName",defaultValue="" ) String loginName,
            @RequestParam(value="phonenumber",defaultValue="" ) String phonenumber,
            @RequestParam (value="sex",defaultValue="" ) String status) {
        //开始查询
        List<User> users = userBizImpl.showUserInfo(loginName,phonenumber,status);
        LayUiTable layUiTable = new LayUiTable();
        layUiTable.setCode(0);
        layUiTable.setMsg("返回消息");
        layUiTable.setData(users);
        return layUiTable;
    }

    /*	查询登录名是否存在*/
    @RequestMapping("/searchLogin")
    @ResponseBody
    public Object searchLogin(String  value) {
        //开始查询
        User user = userBizImpl.selectUserByUsername(value);
        Map map= new HashMap<>();
        if(user!=null){
            map.put("code",MyConstants.successCode);
            map.put("message","登录名已存在");
        }else {
            map.put("code",MyConstants.failCode);
            map.put("message","");
        }
        return map;
    }

    @RequestMapping("/delUser")
    @ResponseBody
    public Object delUser( @RequestParam(value = "ids") String  ids){
        //将json字符串转换成list对象
        List<String> list= (List<String>) JSON.parse(ids);
        int i = userBizImpl.delUserByID(list);
        Map map= new HashMap<>();
        if(i>0){
            map.put("code",MyConstants.successCode);
            map.put("message",MyConstants.delSuccessMsg);
        }else {
            map.put("code",MyConstants.failCode);
            map.put("message",MyConstants.delFailMsg);
        }
        return map;
    }
}
