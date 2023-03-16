package com.kh.mango.user.controller;

import com.kh.mango.point.domain.AdminPoint;
import com.kh.mango.point.domain.PointRecord;
import com.kh.mango.point.service.PointService;
import com.kh.mango.user.domain.PageInfo;
import com.kh.mango.user.domain.User;
import com.kh.mango.user.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserService uService;

    @Autowired
    private PointService pService;

//    @PageableDefault(page = 0, size = 10) Pageable pageable

    @GetMapping("/admin")
    public String adminPage(Model model
               , @RequestParam(value = "page", required = false, defaultValue = "1") Integer page){
        int totalCount = uService.getListCount();
        PageInfo pi = this.getPageInfo(page, totalCount);
        List<User> userList = uService.selectMember(pi);
        int adminPoint = uService.addAllPoint();
        model.addAttribute("pi", pi);
        model.addAttribute("user",userList);
        model.addAttribute("adminPoint",adminPoint);
        model.addAttribute("page", page);
        return "admin";
    }

    private PageInfo getPageInfo(int currentPage, int totalCount) {
        PageInfo pi = null;
        int boardLimit = 15;
        int naviLimit = 5;
        int maxPage;
        int startNavi;
        int endNavi;

        maxPage = (int) ((double) totalCount / boardLimit + 0.9);;
        startNavi = (((int) ((double) currentPage / naviLimit + 0.9)) - 1) * naviLimit + 1;
        endNavi = startNavi + naviLimit -1;
        if(endNavi > maxPage) {
            endNavi = maxPage;
        }
        pi = new PageInfo(currentPage, boardLimit, naviLimit, startNavi, endNavi, totalCount, maxPage);
        return pi;

    }

//    @GetMapping("/admin/search.do")
//    public String userSearchView(@RequestParam("searchValue") String searchValue, Model model) {
//        List<User> searchList = uService.searchUser(searchValue);
//        int adminPoint = uService.addAllPoint();
//        model.addAttribute("user",searchList);
//        model.addAttribute("adminPoint",adminPoint);
//        return "admins";
//    }

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

    @PostMapping("/ajaxSearchUser")
    @ResponseBody
    public List<User> searchUserList(String searchValue) {
        List<User> searchList = uService.searchUser(searchValue);
        return searchList;
    }

}
