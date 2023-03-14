package com.kh.mango.message.controller;

import com.kh.mango.message.domain.Message;
import com.kh.mango.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MessageController {
    @Autowired
    private MessageService mService;

    @PostMapping("/ajaxMessage")
    public List<Message> ajaxMessageView(int userNo){
        List<Message> messages = mService.selectMessageList(userNo);
        return messages;
    }
}
