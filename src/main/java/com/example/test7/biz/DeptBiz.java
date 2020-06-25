package com.example.test7.biz;

import com.example.test7.entity.Dept;
import com.example.test7.entity.Tree;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface DeptBiz {
    List<Tree> selectAll();

    PageInfo<Dept> showDeptInfo(Integer page, Integer limit, Integer visible, String DeptName);
}
