package com.wxss.springbootshiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@RequestMapping("user")
public class PageController {

    @RequestMapping("/hello")
    @ResponseBody
    public String printHello(){
        return "Hello";
    }

    @RequestMapping("/index")
    public String testThymeleaf(Model model){
        return "index";
    }

    @RequestMapping("/toLogin")
    public String login(){
        return "login";
    }

    @RequestMapping("/add")
    public String add(){
        return "/user/add";
    }

    @RequestMapping("/update")
    public String update(){
        return "/user/update";
    }


    @RequestMapping("/delete")
    public String delete(){
        return "/user/delete";
    }

    @RequestMapping("/list")
    public String list(){
        return "/user/list";
    }

    @RequestMapping("/unauthorized")
    public String unauthorized(){
        return "unauthorized";
    }

}
