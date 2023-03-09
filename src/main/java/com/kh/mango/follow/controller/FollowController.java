package com.kh.mango.follow.controller;

import com.kh.mango.user.domain.User;
import com.kh.mango.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
public class FollowController {

    @Autowired
    private UserService uService;

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



    
}
