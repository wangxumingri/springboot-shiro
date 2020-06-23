package com.wxss.springbootshiro.config;

import com.wxss.springbootshiro.shiro.MyRealm;
import com.wxss.springbootshiro.shiro.MyRealm2;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.*;

@Configuration
public class ShiroConfig {

    /**
     * 创建Shiro的过滤器工厂
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 设置登录Url：访问需要 需要认证的资源的时，如果认证未认证，Shiro会重定向到url
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        shiroFilterFactoryBean.setUnauthorizedUrl("unauthorized");
        // 配置过滤器
        /**
         * authc : 需要认证，且通过后访问
         * anon : 不需要认证
         * user : 使用记住我功能时，可以直接访问
         * perms : 具备权限才可以访问、
         * role ： 具备角色才可以访问
         */
        Map<String, String> filterChainDefinitions  = new HashMap<>();
//        filterChainDefinitions.put("/index","anon");
        filterChainDefinitions.put("/login","anon");
//        filterChainDefinitions.put("/logout","anon");

        // 需要认证
        filterChainDefinitions.put("/list","authc");
        // 需要角色认证
        filterChainDefinitions.put("/add","roles[user]");
        filterChainDefinitions.put("/update","roles[admin]");
        // 需要权限认证
        filterChainDefinitions.put("/delete","perms[\"user:delete\"]");
        // 设置所有资源都需要都需要认证
        filterChainDefinitions.put("/**","authc");
        // 默认情况下，退出后，自动跳转到首页
//        filterChainDefinitions.put("/logout","logout");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitions);

//        Map<String, Filter> stringFilterMap = new HashMap<>();
//        stringFilterMap.put("logout1",logoutFilter());
//        shiroFilterFactoryBean.setFilters(stringFilterMap);

        return shiroFilterFactoryBean;
    }
//
//    @Bean
//    public LogoutFilter logoutFilter(){
//        LogoutFilter logoutFilter = new LogoutFilter();
//        logoutFilter.setRedirectUrl("toLogin");
//
//        return logoutFilter;
//    }
    /**
     * 创建Shiro的安全管理器
     * @param
     * @return
     */
    @Bean("defaultWebSecurityManager")
//    public DefaultWebSecurityManager registerDefaultSecurityManager(@Qualifier("modularRealmAuthenticator")Authenticator authenticator){
    public DefaultWebSecurityManager registerDefaultSecurityManager(@Qualifier("myRealm")Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        // 使用1个Realm
         defaultWebSecurityManager.setRealm(realm);

        // 使用多Realm
//        defaultWebSecurityManager.setAuthenticator(authenticator);
        return defaultWebSecurityManager;
    }

    /**
     * 配置插件式认证器模块
     * @param realms
     * @return
     */
    @Bean
    public ModularRealmAuthenticator modularRealmAuthenticator (Realm ...realms){
        ModularRealmAuthenticator modularRealmAuthenticator = new ModularRealmAuthenticator();
        List<Realm> realmList = new ArrayList<>(realms.length);
        Collections.addAll(realmList, realms);
        modularRealmAuthenticator.setRealms(realmList);

        return modularRealmAuthenticator;
    }

    /**
     * 创建安全数据源
     * @return
     */
    @Bean
    public Realm myRealm(@Qualifier("md5CredentialsMatcher") CredentialsMatcher credentialsMatcher){
        MyRealm myRealm = new MyRealm();
        // 加密配置
        myRealm.setCredentialsMatcher(credentialsMatcher);

        return myRealm;
    }


    /**
     * 创建第二个安全数据源
     * @return
     */
    @Bean
    public Realm myRealm2(@Qualifier("sha1CredentialsMatcher") CredentialsMatcher credentialsMatcher){
        MyRealm2 realm2 = new MyRealm2();
        // 加密配置
        realm2.setCredentialsMatcher(credentialsMatcher);

        return realm2;
    }


    @Bean
    public CredentialsMatcher sha1CredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 使用的加密算法
        hashedCredentialsMatcher.setHashAlgorithmName("SHA1");
        // 加密次数
        hashedCredentialsMatcher.setHashIterations(3);
        return hashedCredentialsMatcher;
    }


    @Bean
    public CredentialsMatcher md5CredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 使用的加密算法
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        // 加密次数
        hashedCredentialsMatcher.setHashIterations(3);
        return hashedCredentialsMatcher;
    }


}
