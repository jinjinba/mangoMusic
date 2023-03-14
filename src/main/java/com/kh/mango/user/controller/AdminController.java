package com.kh.mango.user.controller;

import com.kh.mango.point.domain.AdminPoint;
import com.kh.mango.point.domain.PointRecord;
import com.kh.mango.point.service.PointService;
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

    @Autowired
    private PointService pService;

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
        int adminPoint = uService.addAllPoint();
        model.addAttribute("user",searchList);
        model.addAttribute("adminPoint",adminPoint);
        return "admins";
    }

    @GetMapping("/admin/userInfo")
    public String userInfoView(@RequestParam("userNo") int userNo, Model model) {
        User user = uService.selectOneByNumber(userNo);
        model.addAttribute("user", user);
        return "userInfo";
    }

    @GetMapping("/admin/pointList")
    public String allPointRecordList(Model model) {
        List<User> recordList = pService.allPointList();
        model.addAttribute("recordList", recordList);
        return "allPointRecordList";
    }

}
