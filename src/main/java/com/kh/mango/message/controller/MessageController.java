//package com.kh.mango.message.controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.kh.mango.message.domain.Message;
//import com.kh.mango.message.domain.MessageList;
//import com.kh.mango.message.domain.MessageUser;
//import com.kh.mango.message.service.MessageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.List;
//
//@Controller
//public class MessageController {
//    @Autowired
//    private MessageService mService;
//
//    @PostMapping("/ajaxMessage")
//    @ResponseBody
//    public ResponseEntity<String> ajaxMessageView(int userNo){
//        List<MessageList> messages = mService.selectMessageList(userNo);
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonString = "";
//
//        try {
//            jsonString = objectMapper.writeValueAsString(messages);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            return new ResponseEntity<>("{\"error\":\"JsonProcessingException\"}", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        return new ResponseEntity<>(jsonString, HttpStatus.OK);
//    }
//
//    @PostMapping("/ajaxMsgUser")
//    @ResponseBody
//    public String ajaxMsgUser(int sendUserNo, int receiveUserNo){
//        MessageUser mUser = new MessageUser(sendUserNo,receiveUserNo);
//        List<Message> mList = mService.selectMessageListAll(mUser);
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonString = "";
//
//        try {
//            jsonString = objectMapper.writeValueAsString(mList);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            return jsonString;
//        }
//
//        return jsonString;
//    }
//
//
//
//
//    @PostMapping("/ajaxMsgSend")
//    @ResponseBody
//    public String ajaxMsgSend(String msgContent, int sendUser, int receiveUser){
//        Message message = new Message(msgContent,sendUser,receiveUser);
//        int result = mService.insertMsgSend(message);
//        if(result > 0){
//            return "result";
//        }else {
//            return null;
//        }
//    }
//
//    @PostMapping("/ajaxChatRemove")
//    @ResponseBody
//    public String ajaxChatRemove(int roomNo){
//        int result = mService.deleteRoomRemove(roomNo);
//        return "success";
//    }
//
//    // 초기 채팅방 로딩
//    @PostMapping("/ajaxLoadChatRoom")
//    @ResponseBody
//    public String ajaxLoadChatRoom(int userNo){
//        List<Message> mList = mService.selectChatRoomList(userNo);
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonString = "";
//
//        try {
//            jsonString = objectMapper.writeValueAsString(mList);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            return jsonString;
//        }
//
//        return jsonString;
//    }
//
//
//    @PostMapping("/ajaxSelectChatRoom")
//    @ResponseBody
//    public String ajaxSelectChatRoom(int userNo,int userNo2){
//        Message message = new Message(userNo,userNo2);
//        List<Message> mList = mService.selectChatRoom(message);
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonString = "";
//        try {
//            jsonString = objectMapper.writeValueAsString(mList);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            return jsonString;
//        }
//
//        return jsonString;
//    }
//}
