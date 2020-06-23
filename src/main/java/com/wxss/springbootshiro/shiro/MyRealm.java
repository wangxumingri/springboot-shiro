package com.wxss.springbootshiro.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.ByteSource.Util;

import java.util.*;

public class MyRealm extends AuthorizingRealm {
    /**
     * 模拟数据库账号和密码
     */
    private static final Map<String,String> ACCOUNT_PASSWORD_MAP = new HashMap<>();
    private static final Map<String,String> ACCOUNT_SALT_MAP = new HashMap<>();

    {
        ACCOUNT_PASSWORD_MAP.put("admin","9aa75c4d70930277f59d117ce19188b0");
        ACCOUNT_PASSWORD_MAP.put("test","c39fcff9da370d9d9b3b24a929a7efc5");

        ACCOUNT_SALT_MAP.put("admin", "admin");
        ACCOUNT_SALT_MAP.put("test","test");

    }

    /**
     * 权限校验
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权");
        // 登录账号
        Object principal = principalCollection.getPrimaryPrincipal();
        // 角色集合
        Set<String> roles = new HashSet<>();
        roles.add("user");
        if ("admin".equals(principal)){
            roles.add("admin");
        }
        // 模拟授权
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo(roles);
        return authorizationInfo;
    }

    /**
     * 用户认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证");

        // 认证的实体信息:可以是username，或者封装的实体
        Object principal = authenticationToken.getPrincipal();
        // 校验账号
        if (null == ACCOUNT_PASSWORD_MAP.get(principal)){
            throw new UnknownAccountException("用户不存在");
        }
        // 当前realm对象的name
        String realmName = getName();
        // 盐值：唯一字符串，可使用uuid或者用户名，在用户注册时，和注册信息一起保存到数据库中
        ByteSource salt = ByteSource.Util.bytes(ACCOUNT_SALT_MAP.get(principal));
        // 校验密码
        /**
         * 1. 用户名
         * 2. 用户在数据库中的密码
         * 3. 该用户密码加密盐值
         * 4.Realm的名称，调用父类的getName()方法即可
         */
        return  new SimpleAuthenticationInfo(principal,ACCOUNT_PASSWORD_MAP.get(principal),salt, realmName);
    }


    public static void main(String[] args) {
        String algorithmName = "MD5";
        int hashIterations = 3;

        Object credential = "123456";
        ByteSource salt = ByteSource.Util.bytes("admin");
        SimpleHash simpleHash = new SimpleHash(algorithmName, credential, salt, hashIterations);
        // 9aa75c4d70930277f59d117ce19188b0
        System.out.println("admin 盐值加密结果: "+ simpleHash.toString());

        credential = "qwerty";
        salt = ByteSource.Util.bytes("test");
        simpleHash = new SimpleHash(algorithmName, credential, salt, hashIterations);

        // c39fcff9da370d9d9b3b24a929a7efc5
        System.out.println("test 盐值加密结果: "+ simpleHash.toString());

    }
}
