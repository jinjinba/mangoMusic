package com.kh.mango.message.service;

import com.kh.mango.message.domain.MessageList;

import java.util.List;

public interface MessageService {
    List<MessageList> selectMessageList(int userNo);
}
