package com.wxss.springbootshiro.config;

import com.wxss.springbootshiro.shiro.MyRealm;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     * 创建Shiro的过滤器工厂
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(registerDefaultSecurityManager(myRealm()));

        shiroFilterFactoryBean.setLoginUrl("/login");
        // 配置过滤器
        /**
         * authc : 需要认证，且通过后访问
         * anon : 不需要认证
         * user : 使用记住我功能时，可以直接访问
         * perms : 具备权限才可以访问、
         * role ： 具备角色才可以访问
         */
        Map<String, String> filterChainDefinitions  = new HashMap<>();

        filterChainDefinitions.put("/testThymeleaf","anon");
//        filterChainDefinitions.put("/add","authc");
//        filterChainDefinitions.put("/update","authc");

        filterChainDefinitions.put("/*","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitions);
        return shiroFilterFactoryBean;
    }

    /**
     * 创建Shiro的安全管理器
     * @param realm
     * @return
     */
    @Bean("defaultWebSecurityManager")
    public DefaultWebSecurityManager registerDefaultSecurityManager(@Qualifier("myRealm") Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);

        return defaultWebSecurityManager;
    }

    /**
     * 创建安全数据源
     * @return
     */
    @Bean
    public Realm myRealm(){
        MyRealm myRealm = new MyRealm();

        return myRealm;
    }

}
