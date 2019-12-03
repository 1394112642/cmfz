package com.baizhi.controller;

import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;


    @RequestMapping("/out")
    public String out(HttpSession session) {
        session.invalidate();
        return "redirect:/login/login.jsp";
    }

    //后台登录
    @ResponseBody
    @RequestMapping("login")
    public String login(String username, String password, String code, HttpSession session) {
        return adminService.selectUsername(username, password, code, session);
    }

}
