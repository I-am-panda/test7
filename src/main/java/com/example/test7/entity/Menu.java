package com.example.test7.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Menu {
    private Integer menuId;    //菜单ID

    private String menuName;   //菜单名称

    private Integer parentId;   //父菜单ID

    private String fatherName;   //菜单父名称

    private Integer orderNum;   //显示顺序

    private String url;        //请求地址

    private String menuType;  //菜单类型（M目录 C菜单 F按钮）

    private String visible;   //菜单状态（0显示 1隐藏）

    private String perms;      //权限标识

    private String icon;       //菜单图标

    private String createBy;   //创建者

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;   //创建时间

    private String updateBy;   //更新者

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;   //更新时间

    private String remark;     //备注
}