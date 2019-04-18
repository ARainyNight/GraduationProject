package com.nuc.shg.controller;

import com.nuc.shg.biz.GlobalBiz;
import com.nuc.shg.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/***
 *  ClassName : GlobalController
 *  Author    : lin
 *  Date      : 2019/4/17 14:53    
 *  Remark    : 
 */

@Controller("globalController")
public class GlobalController {

    @Autowired
    private GlobalBiz globalBiz;

    @RequestMapping("/toLogin")
    public String toLogin(HttpSession session){
        session.setAttribute("admin",null);
        return "login";
    }


    @RequestMapping("/login")
    public String login(HttpSession session, @RequestParam String username, @RequestParam String password){
        Admin admin = globalBiz.login(username,password);
        if (admin==null){
            return "redirect:toLogin";
        }
        session.setAttribute("admin",admin);
        return "redirect:admin/adminList";
    }

    @RequestMapping("/quit")
    public String quit(HttpSession session){
       session.setAttribute("admin",null);
        return "redirect:toLogin";
    }



}
