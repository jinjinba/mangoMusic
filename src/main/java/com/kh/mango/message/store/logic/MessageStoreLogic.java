package com.kh.mango.message.store.logic;

import com.kh.mango.message.domain.Message;
import com.kh.mango.message.store.MessageStore;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageStoreLogic implements MessageStore {
    @Autowired
    private SqlSession session;


    @Override
    public Message insertChatRoom(Message message) {
        session.insert("MessageMapper.insertChatRoomUser",message);
        session.insert("MessageMapper.insertChatRoom",message);
        return session.selectOne("MessageMapper.selectChatRoomJoinRoomUser");
    }
}
