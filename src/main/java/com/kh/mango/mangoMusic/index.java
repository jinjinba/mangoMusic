package com.kh.mango.mangoMusic;

import org.springframework.web.bind.annotation.GetMapping;

public class index {

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
