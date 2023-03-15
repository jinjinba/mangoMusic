package com.kh.mango.follow.controller;

import com.kh.mango.follow.domain.Follow;
import com.kh.mango.follow.service.FollowService;
import com.kh.mango.point.domain.Point;
import com.kh.mango.user.domain.User;
import com.kh.mango.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class FollowController {

    @Autowired
    private UserService uService;

    @Autowired
    private FollowService followService;


    @GetMapping("/userSearch")
    public String userSearch() {
        return "userSearch";
    }

    @GetMapping("/userList")
    public String userList(@RequestParam("searchValue") String searchValue, Model model) {
        List<User> searchList = uService.searchUser(searchValue);
        model.addAttribute("user",searchList);
        return "userList";
    }

//    @GetMapping("/userFollow")
//    public String followUser(Model model
//            , @SessionAttribute("loginUser") User user
//            ,@RequestParam("userNo")int followNo) {
//        Follow followUser = new Follow(user.getUserNo(), followNo);
//        int result = followService.followUser(followUser);
//        if(result > 0) {
//            return "/userSearch";
//        } else {
//            return "/index";
//        }
//    }

    @PostMapping("/ajaxFollowUser")
    @ResponseBody
    public String ajaxFollowUser(@SessionAttribute("loginUser") User user
            , int followNo){
        Follow followUser = new Follow(user.getUserNo(), followNo);
        int result = followService.followUser(followUser);

        if(result > 0) {
            return "/userSearch";
        } else {
            return "/index";
        }
    }

    
}
