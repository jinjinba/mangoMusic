package com.kh.mango.message.store;

import com.kh.mango.message.domain.Message;
import com.kh.mango.message.domain.MessageList;
import com.kh.mango.message.domain.MessageUser;

import java.util.List;

public interface MessageStore {
    List<MessageList> selectMessageList(int userNo);

    List<Message> selectMessageListAll(MessageUser mUser);
}
