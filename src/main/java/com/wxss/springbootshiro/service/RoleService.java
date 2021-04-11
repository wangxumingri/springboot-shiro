package com.wxss.springbootshiro.service;

import com.wxss.springbootshiro.domain.SysRole;

import java.util.List;

public interface RoleService {
    /**
     * 获取用户角色
     * @param userId
     * @return
     */
    List<SysRole> getRoleByUserId(Long userId);
}
