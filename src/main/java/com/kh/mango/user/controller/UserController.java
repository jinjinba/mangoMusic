package com.kh.mango.user.controller;

import com.kh.mango.user.domain.Mypage;
import com.kh.mango.user.domain.User;
import com.kh.mango.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

@Controller
@Slf4j
public class UserController {
    @Autowired
    private UserService uService;


    @GetMapping("/register")
    public String register() {
        return "user/register";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String loginView() {
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login.do")
    public String userLogin(
            HttpServletRequest request
            , @RequestParam("user-id") String userId
            , @RequestParam("user-pw") String userPw
            , Model model) {
        try {
            User uParam = new User(userId, userPw);
            User user = uService.checkUserLogin(uParam);
            HttpSession session = request.getSession();
            if (user != null) {
                session.setAttribute("loginUser", user);
                return "index";
            }
            model.addAttribute("msg","로그인 실패");
            return "error";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", e.getMessage());
            return "error";
        }
    }


    @GetMapping("/mypage")
    public String myPageView(Model model,@SessionAttribute("loginUser") User user){
        Mypage myPage = uService.mypageInfo(user.getUserNo());
        model.addAttribute("myPage",myPage);
        return "mypage";
    }


}
