package com.wxss.springbootshiro.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyLogoutFilter extends LogoutFilter {

    public MyLogoutFilter() {
       super.setRedirectUrl("/toLogin");
    }

    /**
     * 自定义登出,登出之后,清理当前用户redis部分缓存信息
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {

        //登出操作 清除缓存  subject.logout() 可以自动清理缓存信息, 这些代码是可以省略的  这里只是做个笔记 表示这种方式也可以清除
        Subject subject = getSubject(request,response);
//        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
//        MyRealm myRealm = (MyRealm) securityManager.getRealms().iterator().next();
//        PrincipalCollection principals = subject.getPrincipals();
//        myRealm.clearCache(principals);

        System.out.println("自定义过滤逻辑");
        //登出
        subject.logout();

        //获取登出后重定向到的地址
        String redirectUrl = super.getRedirectUrl(request,response,subject);
        //重定向
        super.issueRedirect(request,response,redirectUrl);

        return false;
    }

}
