package com.kh.mango.point.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PointController {

    @GetMapping("/point")
    public String pointUpdate(){
        return "mypage.mustache";
    }
}
