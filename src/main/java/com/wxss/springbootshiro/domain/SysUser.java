package com.wxss.springbootshiro.domain;

public class SysUser {
    /**
    * 主键
    */
    private Long userId;

    /**
    * 用户名
    */
    private String userName;

    /**
    * 登录名
    */
    private String loginName;

    /**
    * 密码
    */
    private String password;

    /**
    * 盐
    */
    private String salt;

    /**
    * 手机号
    */
    private String phoneNumber;

    /**
    * 用户状态: NORMAL -正常，DISABLE-禁用
    */
    private String status;

    /**
    * 删除标识：1-存在，2-删除
    */
    private Boolean delFlag;

    private Boolean isAdmin;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
}