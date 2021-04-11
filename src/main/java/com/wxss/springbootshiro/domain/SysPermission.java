package com.wxss.springbootshiro.domain;

public class SysPermission {
    /**
    * 权限id
    */
    private Long permId;

    /**
    * 权限名称
    */
    private String permName;

    /**
    * 权限代码
    */
    private String permCode;

    /**
    * 权限类型：MENU-菜单，BUTTON-按钮
    */
    private String permType;

    public Long getPermId() {
        return permId;
    }

    public void setPermId(Long permId) {
        this.permId = permId;
    }

    public String getPermName() {
        return permName;
    }

    public void setPermName(String permName) {
        this.permName = permName;
    }

    public String getPermCode() {
        return permCode;
    }

    public void setPermCode(String permCode) {
        this.permCode = permCode;
    }

    public String getPermType() {
        return permType;
    }

    public void setPermType(String permType) {
        this.permType = permType;
    }
}