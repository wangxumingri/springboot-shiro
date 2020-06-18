package com.wxss.springbootshiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("user")
public class UserController {

    @RequestMapping("/hello")
    @ResponseBody
    public String printHello(){
        return "Hello";
    }

    @RequestMapping("/testThymeleaf")
    public String testThymeleaf(Model model){
        model.addAttribute("message","Hello World");
        return "testThymeleaf";
    }

    @RequestMapping("/add")
    public String add(){
        return "/user/add";
    }

    @RequestMapping("/update")
    public String update(){
        return "/user/update";
    }

    @RequestMapping("/login")
    public String login(){
        return "/login";
    }

}
