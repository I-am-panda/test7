package com.example.test7.dao;

import com.example.test7.entity.Dept;
import com.example.test7.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DeptMapper {
    int deleteByPrimaryKey(Integer deptId);

    int insert(Dept record);

    int insertSelective(Dept record);

    Dept selectByPrimaryKey(Integer deptId);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);

    List<Dept> selectAllDept();

    List<Dept> selectDeptInfo(Integer visible, String deptName);

    Dept selectDeptNameById(int fatherId);
}