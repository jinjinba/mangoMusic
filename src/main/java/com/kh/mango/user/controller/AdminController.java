package com.kh.mango.user.controller;

import com.kh.mango.point.domain.AdminPoint;
import com.kh.mango.user.domain.User;
import com.kh.mango.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserService uService;

    @GetMapping("/admin")
    public String adminPage(Model model){
        List<User> userList = uService.selectMember();
        int adminPoint = uService.addAllPoint();
        model.addAttribute("user",userList);
        model.addAttribute("adminPoint",adminPoint);
        return "admin";
    }

    @GetMapping("/admin/search.do")
    public String userSearchView(@RequestParam("searchValue") String searchValue, Model model) {
        List<User> searchList = uService.searchUser(searchValue);
        model.addAttribute("user",searchList);
        return "admins";
    }

    @GetMapping("/admin/userInfo")
    public String userInfoView(@RequestParam("userNo") int userNo, Model model) {
        User user = uService.selectOneByNumber(userNo);
        model.addAttribute("user", user);
        return "userInfo";
    }

}
