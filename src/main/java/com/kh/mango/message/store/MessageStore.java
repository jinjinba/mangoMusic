package com.kh.mango.message.store;

import com.kh.mango.message.domain.MessageList;

import java.util.List;

public interface MessageStore {
    List<MessageList> selectMessageList(int userNo);
}
