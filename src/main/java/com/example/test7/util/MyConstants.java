package com.example.test7.util;

import java.util.Date;

public class MyConstants {
    //增删改成功
    public static final int successCode=0;
    //增删改失败
    public static final int failCode=1;
    public static final String saveSuccessMsg="添加成功";
    public static final String  saveFailMsg="添加失败";
    public static final String editSuccessMsg="修改成功";
    public static final String  editFailMsg="修改失败";
    public static final String delSuccessMsg="删除成功";
    public static final String  delFailMsg="删除失败";
    //加密算法
    public static final String algorithmName="MD5";
    //加密次数
    public static final   int hashIterations=1000;

    //获取系统当前时间
    public static Date getDate(){
        Date now=new Date();
        return now;
    }

    public static void main(String[] args) {
        MyConstants myConstants = new MyConstants();
        System.out.println(myConstants.getDate());
    }
}
