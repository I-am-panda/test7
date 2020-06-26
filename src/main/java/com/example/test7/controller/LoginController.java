package com.example.test7.controller;

import com.example.test7.biz.MenuBiz;
import com.example.test7.entity.LayUiTree;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class LoginController {
    @Autowired
    private MenuBiz menuBiz;
    /**
     * 将请求toLogin转发到登录页面login.html
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }
    @RequestMapping("/login")
    public String login(String username, String password, Model model ,String captcha){
        //登录验证
        System.out.println("username:"+username+"   password:"+password+"   captcha:"+captcha);
        if(captcha==null){
            model.addAttribute("message","验证码不能为空");
            return "login";
        }
        //获取shiro的主体
        Subject subject = SecurityUtils.getSubject();
        //构建用户登录令牌
        UsernamePasswordToken token= new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
        }catch (UnknownAccountException e){
            model.addAttribute("message","用户名错误");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("message","密码错误");
            return "login";
        }
        //将要去index页面之前，保存部分数据到model
        model.addAttribute("loginName",username);
        //放入所有的菜单，根据当前登录的用户
        List<LayUiTree> menus = menuBiz.selectAllMenuByName(username);
        model.addAttribute("menus",menus);
        return "index";
    }

    /**
     * 注销
     * @return
     */
    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
}
