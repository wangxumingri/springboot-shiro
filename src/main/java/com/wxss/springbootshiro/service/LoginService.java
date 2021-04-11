package com.wxss.springbootshiro.service;

import com.wxss.springbootshiro.common.enums.UserStatus;
import com.wxss.springbootshiro.domain.SysUser;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class LoginService {
    private static final int PASSWORD_MIN_LENGTH = 8;
    private static final int PASSWORD_MAX_LENGTH = 16;
    private static final int USERNAME_MIN_LENGTH = 8;
    private static final int USERNAME_MAX_LENGTH = 16;



    @Autowired
    private UserService userService;

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    public SysUser login(String username, String password) {
        // TODO 验证码

        // 用户名或密码为空 错误
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new AuthenticationException("用户名或密码不能为空");
        }

        // 用户名或密码如果不在指定范围内 错误
//        if (password.length() < PASSWORD_MIN_LENGTH
//                || password.length() > PASSWORD_MAX_LENGTH
//                || username.length() < USERNAME_MIN_LENGTH
//                || username.length() > USERNAME_MAX_LENGTH) {
//            throw new AuthenticationException("用户名或密码长度为6-16");
//        }

        // 查询用户
//        SysUser user = userService.getUserByUsernameAndPassword(username, password);
        SysUser user = userService.getUserByLoginName(username);

        // 用户不存在
        if (Objects.isNull(user)) {
            throw new AuthenticationException("用户不存在");
        }

        // 用户已被删除 错误
        if (user.getDelFlag()){
            throw new AuthenticationException("用户不存在");
        }

        // 用户状态判断
        if (!UserStatus.NORMAL.name().equalsIgnoreCase(user.getStatus())){
            throw new AuthenticationException("用户已被禁用，请联系管理员");
        }

        // 密码匹配:交由Shiro进行校验


        return user;
    }
}
