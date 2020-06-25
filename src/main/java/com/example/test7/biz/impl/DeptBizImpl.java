package com.example.test7.biz.impl;

import com.example.test7.biz.DeptBiz;
import com.example.test7.dao.DeptMapper;
import com.example.test7.entity.Dept;
import com.example.test7.entity.Tree;
import com.example.test7.util.DeptTree;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptBizImpl implements DeptBiz {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Tree> selectAll() {
        //查询所有的菜单
        List<Dept> dept =deptMapper.selectAllDept();
        //并组装成tree格式的
        return DeptTree.getChildPerms(dept, 0);
    }

    @Override
    public PageInfo<Dept> showDeptInfo(Integer page, Integer limit, Integer visible, String DeptName) {
        //开始分页,第一个参数是当前第几页，第二个参数是一页显示多少行
        PageHelper.startPage(page,limit);
        int fatherId;
        Dept dept=null;
        List<Dept> DeptInfo = deptMapper.selectDeptInfo(visible,DeptName);
        for(Dept m : DeptInfo)
        {
            fatherId=m.getParentId();
            dept=deptMapper.selectDeptNameById(fatherId);
            if(dept!=null)
                m.setFatherName(dept.getDeptName());
        }
        //结束分页,pageInfo封装了分页之后所有数据
        PageInfo<Dept> pageInfo = new PageInfo<Dept>(DeptInfo);
        return pageInfo;
    }

    /*@Override
    public void addDept(Dept Dept) {
        // TODO Auto-generated method stub

        DeptMapper.insert(Dept);
    }

    @Override
    public int deleteDept(Integer DeptID) {
        // TODO Auto-generated method stub
        DeptMapper.deleteByPrimaryKey(DeptID);
        return 0;
    }

    @Override
    public int delDeptsByID(List<String> list) {
        // TODO Auto-generated method stub
        return DeptMapper.delDeptsByID(list);
    }

    @Override
    public void updateDept(Dept Dept) {
        // TODO Auto-generated method stub
        DeptMapper.updateByPrimaryKey(Dept);
    }*/
}
