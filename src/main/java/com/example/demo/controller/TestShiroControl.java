package com.example.demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.apache.shiro.subject.Subject;

import java.util.Map;

/**
 * @author Duan
 * @date 2020/5/21 - 15:47
 */
@RestController
//@RequestMapping(value = "/user", produces = "application/json;charset=UTF-8")
public class TestShiroControl {
    /**
     * 测试方法
     */
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(Model model) {
        System.out.println("TestShiroControl.hello");
            model.addAttribute("hello","世界");
        return "test";
    }

    @RequestMapping("/add")
    public String add() {
        return "/user/add";
    }

    @RequestMapping("/update")
    public String update() {
        return "/user/update";
    }

    //没有登录跳转页面
    @RequestMapping("/toLogin")
    public String login() {
        return "/user/login";
    }
    //没有访问权限跳转页
    @RequestMapping("/nonAuth")
    public String noAuth() {
        return "/user/noAuth";
    }


    @RequestMapping("/testThymeleaf")
    public String thymeleaf(Model model) {
        //将数据存入modle
        model.addAttribute("name","hello word!");
        return "test";
    }

    @RequestMapping("/login")
    public String login(String Name,String password,Model model) {
        //使用shiro编写认证操作
        //1获取subject
        Subject subject=SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken token=new UsernamePasswordToken(Name,password);
        //3.执行登录方法
        try {
            subject.login(token);
            //登录成功后进入主页
            return "test";
        }
        catch (UnknownAccountException e){
            //登录失败返回用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "redirect:/toLogin";
        }catch (IncorrectCredentialsException e){
            //登录失败返回用户密码错误
            model.addAttribute("msg","用户密码错误");
            return "redirect:/toLogin";
        }

    }
}
