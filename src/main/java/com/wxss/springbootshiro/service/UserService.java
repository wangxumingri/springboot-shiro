package com.wxss.springbootshiro.service;

import com.wxss.springbootshiro.domain.SysPermission;
import com.wxss.springbootshiro.domain.SysRole;
import com.wxss.springbootshiro.domain.SysUser;
import com.wxss.springbootshiro.domain.SysUserRole;

import java.util.List;

public interface UserService {
    /**
     * 查询用户
     * @param loginName
     * @param password
     * @return
     */
    SysUser getUserByUsernameAndPassword(String loginName,String password);


    /**
     * 根据用户名查询用户
     * @param loginName
     * @return
     */
    SysUser getUserByLoginName(String loginName);
}
