package com.kh.mango.message.store.logic;

import com.kh.mango.message.domain.Message;
import com.kh.mango.message.domain.MessageList;
import com.kh.mango.message.domain.MessageUser;
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
    public List<MessageList> selectMessageList(int userNo) {
        return session.selectList("MessageMapper.selectMessageList",userNo);
    }

    @Override
    public List<Message> selectMessageListAll(MessageUser mUser) {
        return session.selectList("MessageMapper.selectMessageListAll",mUser);
    }

    @Override
    public int insertMsgSend(Message message) {
        session.insert("MessageMapper.insertMsgSend2",message);
        return session.insert("MessageMapper.insertMsgSend",message);
    }

    @Override
    public List<Message> selectChatRoomList(int userNo) {
        return session.selectList("MessageMapper.selectMessageList",userNo);
    }

    @Override
    public List<Message> selectChatRoom(int userNo) {
        return session.selectList("MessageMapper.selectChatRoom",userNo);

    }

    @Override
    public int deleteRoomRemove(int roomNo) {
        return session.delete("MessageMapper.deleteChatRoom",roomNo);
    }
}
