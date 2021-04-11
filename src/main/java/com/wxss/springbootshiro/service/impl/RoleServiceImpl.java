package com.wxss.springbootshiro.service.impl;

import com.wxss.springbootshiro.domain.SysRole;
import com.wxss.springbootshiro.mapper.SysRoleMapper;
import com.wxss.springbootshiro.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private SysRoleMapper roleMapper;

    @Override
    public List<SysRole> getRoleByUserId(Long userId) {
        return roleMapper.selectRoleByUserId(userId);
    }
}
