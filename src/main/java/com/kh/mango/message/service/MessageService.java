package com.kh.mango.message.service;

import com.kh.mango.message.domain.Message;

import java.util.List;

public interface MessageService {
    List<Message> selectMessageList(int userNo);
}
