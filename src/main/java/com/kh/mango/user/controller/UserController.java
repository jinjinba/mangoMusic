package com.kh.mango.user.controller;

import com.kh.mango.user.domain.User;
import com.kh.mango.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

@Controller
@Slf4j
public class UserController {
    @Autowired
    private UserService uService;

    @GetMapping("/test")
    public String User(Model model){
        User user = uService.test();
        model.addAttribute("user",user);
        return "test";
    }

    @GetMapping("/mypage")
    public String myPage(){
        return "mypage";
    }


}
