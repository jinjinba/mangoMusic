package com.kh.mango.message.service.logic;

import com.kh.mango.message.domain.Message;
import com.kh.mango.message.domain.MessageList;
import com.kh.mango.message.domain.MessageUser;
import com.kh.mango.message.service.MessageService;
import com.kh.mango.message.store.MessageStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageStore mStore;

    @Override
    public List<MessageList> selectMessageList(int userNo) {
        return mStore.selectMessageList(userNo);
    }

    @Override
    public List<Message> selectMessageListAll(MessageUser mUser) {
        return mStore.selectMessageListAll(mUser);
    }

    @Override
    public int insertMsgSend(Message message) {
        return mStore.insertMsgSend(message);
    }

    @Override
    public List<Message> selectChatRoomList(int userNo) {
        return mStore.selectChatRoomList(userNo);
    }

    @Override
    public List<Message> selectChatRoom(int userNo) {
        return mStore.selectChatRoom(userNo);
    }
}
