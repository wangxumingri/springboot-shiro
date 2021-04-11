package com.wxss.springbootshiro.service;

import com.wxss.springbootshiro.domain.SysPermission;

import java.util.List;

public interface PermissionService {

    /**
     * 获取用户权限
     * @param userId
     * @return
     */
    List<SysPermission> getPermissionByUserId(Long userId);
}
