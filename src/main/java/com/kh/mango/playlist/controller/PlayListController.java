package com.kh.mango.playlist.controller;

import com.kh.mango.playlist.domain.PlayList;
import com.kh.mango.playlist.service.PlayListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("/followPlaylist")
    public String followPlaylist() {
        return "followPlaylist";
    }

    @PostMapping("/ajaxAddPlaylist")
    @ResponseBody
    public String ajaxAddPlaylist(int userNo, String name, String artist) {
        PlayList playlistParam = new PlayList(userNo, name, artist);
        int playlist = pService.addPlaylist(playlistParam);
        return "success";
    }
}
