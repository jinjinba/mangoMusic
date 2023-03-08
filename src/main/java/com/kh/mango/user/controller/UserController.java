package com.kh.mango.user.controller;

import com.kh.mango.user.domain.Mypage;
import com.kh.mango.user.domain.User;
import com.kh.mango.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

@Controller
@Slf4j
public class UserController {
    @Autowired
    private UserService uService;

//    @GetMapping("/test")
//    public String User(Model model){
//        User user = uService.test();
//        model.addAttribute("user",user);
//        return "test";
//    }

    @GetMapping("/admin")
    public String memberList(Model model){
        List<User> userList = uService.selectMember();
        model.addAttribute("user",userList);
        return "admin";
    }

    @GetMapping("/admin/search")
    public String userSearchView(@RequestParam("searchValue") String searchValue, Model model) {
        List<User> searchList = uService.searchUser(searchValue);
        if(!searchList.isEmpty()) {
            model.addAttribute("searchUser", searchList);
            return "adminSearch";
        } else {
            return "admin";
        }
    }

    @GetMapping("/mypage")
    public String myPage(Model model){
        Mypage myPage = uService.mypageInfo();
        model.addAttribute("user",myPage);
        return "mypage";
    }




}
