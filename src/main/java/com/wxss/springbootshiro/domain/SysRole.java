package com.wxss.springbootshiro.domain;

public class SysRole {
    private Long roleId;

    /**
    * 角色名称
    */
    private String roleName;

    /**
    * 角色代码
    */
    private String roleCode;

    /**
    * 角色状态: NORMAL -正常，DISABLE-禁用
    */
    private String status;

    /**
    * 删除标识：1-存在，2-删除
    */
    private Boolean delFlag;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }
}