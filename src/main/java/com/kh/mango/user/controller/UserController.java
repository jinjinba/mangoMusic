package com.kh.mango.user.controller;

import com.kh.mango.point.domain.PointRecord;
import com.kh.mango.user.domain.*;
import com.kh.mango.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
public class UserController {
    @Autowired
    private UserService uService;

    // 회원가입 화면
    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public String register() {
        return "register";
    }
    // 유저 회원가입
    @RequestMapping(method = RequestMethod.POST, value = "/register.do")
    public String userRegister(
            HttpServletRequest request
            , @ModelAttribute User user
            , Model model) {
        try {
            int result = uService.insertUser(user);
            if(result > 0) {
                return "index";
            }else {
                model.addAttribute("msg", "회원가입이 실패인데요?");
                return "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", e.getMessage());
            return "error";
        }
    }

    // 로그인 화면
    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String loginView() {
        return "login";
    }

    // 유저 로그인
    @RequestMapping(method = RequestMethod.POST, value = "/login")
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
    // 로그아웃
    @GetMapping("/logout")
    public String userLogout(HttpSession session, Model model) {
        if(session != null) {
            session.invalidate();
            return "index";
        }else {
            model.addAttribute("msg", "로그아웃 처리 하지 못하였습니다.");
            return "error";
        }
    }


    @GetMapping("/mypage")
    public String myPageView(Model model,@SessionAttribute("loginUser") User user){
        MyPage myPage = uService.myPageInfo(user.getUserNo());
        List<MyPageFollow> followList = uService.myPageFollow(user.getUserNo());
        List<MyPageDeals> deals = uService.myPageDeals(user.getUserNo());
        List<Like> like = uService.myPageLikes(user.getUserNo());
        List<PointRecord> pointRecordsList = uService.selectPointRecord(user.getUserNo());
        model.addAttribute("myPage",myPage);
        model.addAttribute("followers",followList);
        model.addAttribute("deals",deals);
        model.addAttribute("likes",like);
        model.addAttribute("pointRecord",pointRecordsList);
        return "mypage";
    }



}
