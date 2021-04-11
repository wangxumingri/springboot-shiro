package com.wxss.springbootshiro.domain;

public class SysRolePermission {
    /**
    * 角色id
    */
    private Long roleId;

    /**
    * 权限id
    */
    private Long permId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermId() {
        return permId;
    }

    public void setPermId(Long permId) {
        this.permId = permId;
    }
}