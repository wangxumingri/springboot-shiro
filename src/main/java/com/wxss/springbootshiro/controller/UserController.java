package com.wxss.springbootshiro.controller;

import com.wxss.springbootshiro.domain.SysPermission;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("user")
public class UserController {

    /**
     * 根据uid查询用户权限
     * @param uid
     * @return
     */
    @GetMapping("permission/{uid}")
    public List<SysPermission> getUserPermissions(@PathVariable("uid") Long uid){
        // 判断用户是否已登录
//        Subject subject = SecurityUtils.getSubject();

        return null;
    }

}
