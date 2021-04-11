package com.wxss.springbootshiro.service.impl;

import com.wxss.springbootshiro.domain.SysPermission;
import com.wxss.springbootshiro.domain.SysRole;
import com.wxss.springbootshiro.mapper.SysPermissionMapper;
import com.wxss.springbootshiro.service.PermissionService;
import com.wxss.springbootshiro.service.RoleService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private SysPermissionMapper sysPermissionMapper;


    @Autowired
    private RoleService roleService;

    @Override
    public List<SysPermission> getPermissionByUserId(Long userId) {
        List<SysRole> roleList = roleService.getRoleByUserId(userId);
        if (CollectionUtils.isNotEmpty(roleList)){
            List<Long> roleIdList = roleList.stream().map(SysRole::getRoleId).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(roleIdList)){
                List<SysPermission> permissionList = sysPermissionMapper.getPermissionByRoleIds(roleIdList);

                return permissionList;

            }
        }

        return null;
    }
}
