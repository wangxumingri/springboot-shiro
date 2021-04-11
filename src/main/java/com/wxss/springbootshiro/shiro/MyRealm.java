package com.wxss.springbootshiro.shiro;

import com.wxss.springbootshiro.domain.SysPermission;
import com.wxss.springbootshiro.domain.SysRole;
import com.wxss.springbootshiro.domain.SysUser;
import com.wxss.springbootshiro.service.LoginService;
import com.wxss.springbootshiro.service.PermissionService;
import com.wxss.springbootshiro.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;


    /**
     * 权限校验
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 登录账号
        Object principal = principalCollection.getPrimaryPrincipal();
        log.info("查询用户权限：{}", principal);
        // 取出用户
        SysUser user = ShiroUtils.getSysUser();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 管理员拥有所有权限
        if (user.getAdmin()) {
            authorizationInfo.addRole("admin");
            authorizationInfo.addStringPermission("*:*:*");
        } else {
            // 角色列表
            List<SysRole> roleList = roleService.getRoleByUserId(user.getUserId());
            // 权限列表
            List<SysPermission> permissionList = permissionService.getPermissionByUserId(user.getUserId());
            Set<String> roles = roleList.stream().map(SysRole::getRoleCode).collect(Collectors.toSet());
            Set<String> permissions = permissionList.stream().map(SysPermission::getPermCode).collect(Collectors.toSet());
            // 角色加入AuthorizationInfo认证对象
            authorizationInfo.setRoles(roles);
            // 权限加入AuthorizationInfo认证对象
            authorizationInfo.setStringPermissions(permissions);
        }

        log.info("查询用户{}权限 返回 ：role {},permission {}",principal,authorizationInfo.getRoles(),authorizationInfo.getStringPermissions());
        return authorizationInfo;
    }

    /**
     * 用户认证
     * 2种思路：
     * 1.自己认证
     * 2.交给shiro认证
     * 采用Shiro认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String username = upToken.getUsername();
        String password = "";
        if (upToken.getPassword() != null) {
            password = new String(upToken.getPassword());
        }

        // 前端传的用户信息:可以是用户名，或者封装的类
        Object principal = authenticationToken.getPrincipal();
        // 前端传的密码
        Object credentials = authenticationToken.getCredentials();

        SysUser user = null;
        try {
            user = loginService.login(username, password);
        } catch (AuthenticationException e) { // TODO 可细分其他异常：重试次数...账号锁定...
            throw new AuthenticationException(e.getMessage(), e);
        } catch (Exception e) {
            log.info("对用户[" + username + "]进行登录验证..验证未通过{}", e.getMessage());
            throw new AuthenticationException(e.getMessage(), e);
        }
        /**
         * 1. 用户名
         * 2. 用户在数据库中的密码
         * 3. 盐值
         * 4. Realm的名称，调用父类的getName()方法即可
         */
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());

        return info;
    }
    public void clearCache(PrincipalCollection principals){
        super.clearCache(principals);
    }



    public static void main(String[] args) {
        String algorithmName = "MD5";
        int hashIterations = 3;

        Object credential = "123456";
        ByteSource salt = ByteSource.Util.bytes("admin");
        System.out.println(salt.toBase64());
        System.out.println(salt.toHex());
        SimpleHash simpleHash = new SimpleHash(algorithmName, credential, salt, hashIterations);
        // 9aa75c4d70930277f59d117ce19188b0
        System.out.println("admin 盐值加密结果: " + simpleHash.toString());

        credential = "qwerty";
        salt = ByteSource.Util.bytes("test");
        simpleHash = new SimpleHash(algorithmName, credential, salt, hashIterations);
        System.out.println(salt.toBase64());
        System.out.println(salt.toHex());
        // c39fcff9da370d9d9b3b24a929a7efc5
        System.out.println("test 盐值加密结果: " + simpleHash.toString());

    }
}
