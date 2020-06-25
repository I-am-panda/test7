package com.example.test7.biz.impl;

import com.example.test7.biz.UserBiz;
import com.example.test7.dao.UserMapper;
import com.example.test7.entity.User;
import com.example.test7.shiro.ShiroUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class UserBizImpl implements UserBiz{
        @Autowired
        private UserMapper userMapper;
        @Override
        public PageInfo<User> selectAllUser(int page, int limit) {
            //开始分页,第一个参数是当前第几页，第二个参数是一页显示多少行
            PageHelper.startPage(page, limit);
            List<User> myUserInfos = userMapper.selectAllUser();
            //结束分页,pageInfo封装了分页之后所有数据
            PageInfo<User> pageInfo = new PageInfo(myUserInfos);
            return pageInfo;

        }

        @Override
        public List<User> showUserInfo(String loginName,String phonenumber,String status ){
            List<User> user=userMapper.selectUserInfo(loginName,phonenumber,status);
            return user;
        }

        /**
         * 按照用户名查询用户信息
         *
         * @param username
         * @return
         */
        @Override
        public User selectUserByUsername(String username) {

            return userMapper.selectUserByUsername(username);
        }

        @Override
        public int insertSelective(User record) {
            //次处要进行密码加盐加密
            String salt = UUID.randomUUID().toString();
            String message = record.getPassword();
            String encryption = ShiroUtil.encryptionBySalt(salt, message);
            record.setPassword(encryption);
            record.setSalt(salt);
            int row = userMapper.insertSelective(record);
            // 新增用户与角色管理
//            insertUserRole(record);
            return row;
        }

    @Override
    public int delUserByID(List<String> ids)
    {
    return userMapper.delUserByID(ids);
    }

    @Override
    public int updatepasswordByID(User record) {
        if (record.getUserId()!=null) {
            String salt = UUID.randomUUID().toString();
            String message = record.getPassword();
            String encryption = ShiroUtil.encryptionBySalt(salt, message);
            record.setPassword(encryption);
            record.setSalt(salt);
            return userMapper.updatepasswordByID(record);
        }
        return 0;
    }

    @Override
        public int updateByPrimaryKeySelective(User record) {

        return userMapper.updateByPrimaryKeySelective(record);
        }
}
