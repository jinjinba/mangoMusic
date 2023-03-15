package com.kh.mango.message.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.mango.message.domain.Message;
import com.kh.mango.message.domain.MessageList;
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

    @PostMapping("/ajaxMessage")
    @ResponseBody
    public ResponseEntity<String> ajaxMessageView(int userNo, Model model){
        List<MessageList> messages = mService.selectMessageList(userNo);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "";

        try {
            jsonString = objectMapper.writeValueAsString(messages);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>("{\"error\":\"JsonProcessingException\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(jsonString, HttpStatus.OK);
    }

}
