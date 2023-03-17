package com.kh.mango.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.mango.message.domain.Message;
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
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public String userRegister(
            HttpServletRequest request
            , @ModelAttribute User user
            , Model model) {
        try {
           uService.insertUser(user);
            model.addAttribute("success", "성공");
           return "index";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", e.getMessage());
            return "/register";
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
//                model.addAttribute("success", 1);
                return "index";
            }else {
                model.addAttribute("error", "에러");
                return "login";
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", e.getMessage());
            return "error";
        }
    }
    // 아이디 찾기
    @RequestMapping(method = RequestMethod.GET, value = "/findId")
    public String findIdView() {
        return "findId";
    }

    @RequestMapping(value = "/findId", method = RequestMethod.POST)
    public String findId(
            HttpServletRequest request
            , @RequestParam("userName") String userName
            , @RequestParam("userEmail") String userEmail
            , Model model) {

        User uParam = new User(userName, userEmail, null);
        User user = uService.findUserId(uParam);
        if(user != null) {
            model.addAttribute("findId", "성공");
            model.addAttribute("user", user);
            return "login";
        }else {
            model.addAttribute("error", "에러");
            return "findId";
        }
    }

    @GetMapping("/newFind")
    public String newFindId(
            @ModelAttribute User userParam
            ,Model model){
//        User uParam = new User(userParam.getUserName(), userParam.getUserEmail(), null);
        User user = uService.findUserId(userParam);
        model.addAttribute("user", user);
        return "newfind";
    }

    // 비밀번호 찾기
    @RequestMapping(method = RequestMethod.GET, value = "/findPw")
    public String findPwView() {
        return "findPw";
    }

    @RequestMapping(value = "/findPw", method = RequestMethod.POST)
    public String findPw(
            HttpServletRequest request
            , @RequestParam("userId") String userId
            , @RequestParam("userName") String userName
            , @RequestParam("userEmail") String userEmail
            , Model model) {
        User uParam = new User(userId,null ,userName , userEmail);
        User user = uService.selectUserPw(uParam);
        if(user != null) {
            model.addAttribute("findPw", "성공");
            model.addAttribute("user", user);
            return "login";
        }else {
            model.addAttribute("error", "에러");
            return "findPw";
        }
    }

    // 비밀번호 변경
    @GetMapping("/updatePw")
    public String updatePwView(
            @ModelAttribute User user
            , Model model){
        model.addAttribute("user", user);
        return "updatePw";
    }
    @PostMapping("/updatePw")
    public String updatePw(
            @ModelAttribute("user")User user
            , @ModelAttribute("newPw") String userPw
            ,Model model){
        User uParam = new User(user.getUserId(),userPw);
        int result = uService.updateUserPw(uParam);
        if(result > 0){
            model.addAttribute("su","성공");
        }
        return "login";
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

    @PostMapping("/ajaxMsgUserSearch")
    @ResponseBody
    public String ajaxMsgUserSearch(String userId){
        List<UserSearch> userList = null;
        if(userId != null && userId != "") {
            userList = uService.selectUserList(userId);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "";
        
        try {
            jsonString = objectMapper.writeValueAsString(userList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return jsonString;
        }
        return jsonString;
    }

    @PostMapping("/ajaxMsgUserAdd")
    @ResponseBody
    public String ajaxMsgUserAdd(int userNo){
        List<UserSearch> uList = uService.selectUserList(userNo);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "";

        try {
            jsonString = objectMapper.writeValueAsString(uList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return jsonString;
        }
        return jsonString;
    }


}
