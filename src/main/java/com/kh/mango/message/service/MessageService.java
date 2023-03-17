package com.kh.mango.message.service;

import com.kh.mango.message.domain.Message;
import com.kh.mango.message.domain.MessageList;
import com.kh.mango.message.domain.MessageUser;

import java.util.List;

public interface MessageService {
    List<MessageList> selectMessageList(int userNo);

    List<Message> selectMessageListAll(MessageUser mUser);

    int insertMsgSend(Message message);

    List<Message> selectChatRoomList(int userNo);
}
