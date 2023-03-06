package com.kh.mango.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @PostMapping("/login")
    public String test(){
    return "login";
    }
    @PostMapping ("/register")
    public String test1(){
        return "register";
    }
}
