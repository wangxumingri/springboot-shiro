package com.wxss.springbootshiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("")
public class LoginController {


    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @PostMapping("/login")
    public String login(String username, String password, RedirectAttributes redirectAttributes){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        if (!subject.isAuthenticated()) {
            try {
                subject.login(usernamePasswordToken);
                redirectAttributes.addFlashAttribute("username", username);
                return "redirect:index";
            } catch (IncorrectCredentialsException e) {
                redirectAttributes.addFlashAttribute("errMsg", "登陆密码错误");
                return "redirect:toLogin";
            } catch (LockedAccountException e) {
                redirectAttributes.addFlashAttribute("errMsg", "用户已被冻结");
                return "redirect:toLogin";
            } catch (AuthenticationException e) {
                redirectAttributes.addFlashAttribute("errMsg", "用户不存在");
                return "redirect:toLogin";
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errMsg", "登陆失败，系统异常");
                return "redirect:toLogin";
            }
        } else {
            return "redirect:index";
        }
    }

    @GetMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:toLogin";
    }
}
