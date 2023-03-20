package com.kh.mango.playlist.controller;

import com.kh.mango.playlist.service.PlayListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class PlayListController {

    @GetMapping("/addPlaylist")
    public String addPlaylist() {
        return "addPlaylist";
    }

    @GetMapping("/followPlaylist")
    public String followPlaylist() {
        return "followPlaylist";
    }
}
