package com.wxss.springbootshiro.service.impl;

import com.wxss.springbootshiro.domain.SysUser;
import com.wxss.springbootshiro.mapper.SysUserMapper;
import com.wxss.springbootshiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private SysUserMapper userMapper;

    @Override
    public SysUser getUserByUsernameAndPassword(String loginName,String password) {
        return userMapper.selectUser(loginName,password);
    }

    @Override
    public SysUser getUserByLoginName(String loginName) {
        return userMapper.selectUserByLoginName(loginName);
    }
}
