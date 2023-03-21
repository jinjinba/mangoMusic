package com.kh.mango.playlist.controller;

import com.kh.mango.playlist.domain.PlayList;
import com.kh.mango.playlist.service.PlayListService;
import com.kh.mango.user.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class PlayListController {

    @Autowired
    private PlayListService pService;

    @GetMapping("/addPlaylist")
    public String addPlaylist() {
        return "addPlaylist";
    }


    @PostMapping("/ajaxAddPlaylist")
    @ResponseBody
    public String ajaxAddPlaylist(int userNo, String name, String artist) {
        PlayList playlistParam = new PlayList(userNo, name, artist);
        int playlist = pService.addPlaylist(playlistParam);
        return "success";
    }

    @GetMapping("/myPlaylist")
    public String myPlaylist(Model model, @SessionAttribute(value = "loginUser", required = false) User user) {
        List<PlayList> myPlaylist = pService.showMyPlaylist(user.getUserNo());
        model.addAttribute("myPlaylist", myPlaylist);
        return "myPlaylist";
    }

    @GetMapping("/followPlaylist")
    public String followPlaylist(Model model, @RequestParam("userNo") String userNo,
                                 @RequestParam("userName") String userName) {
        List<PlayList> followPlaylist = pService.showFollowPlaylist(userNo);
        model.addAttribute("followPlaylist",followPlaylist);
        model.addAttribute("userName", userName);
        return "followPlaylist";
    }

}
