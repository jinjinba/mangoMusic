package com.kh.mango.message.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.mango.message.domain.Message;
import com.kh.mango.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MessageController {
    @Autowired
    private MessageService mService;


    //새로운 메시지 > 검색 > 유저 선택을 통해 chatRoom 생성
    @PostMapping("/ajaxChatStart")
    @ResponseBody
    public String ajaxChatStart(int userNo1, int userNo2){
        Message message = new Message(userNo1,userNo2);
        Message messageResult = mService.insertChatRoom(message);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "";

        try {
            jsonString = objectMapper.writeValueAsString(messageResult);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return jsonString;
        }

        return jsonString;
    }
}
