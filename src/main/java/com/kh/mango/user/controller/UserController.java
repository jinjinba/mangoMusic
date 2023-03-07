package com.kh.mango.user.controller;

import com.kh.mango.user.domain.User;
import com.kh.mango.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService uService;

    @GetMapping("/test")
    public void User(){
        User user = uService.test();
        System.out.println(user.getUserId());
    }
    @GetMapping("/register")
    public String register() {
        return "user/register";
    }


}
