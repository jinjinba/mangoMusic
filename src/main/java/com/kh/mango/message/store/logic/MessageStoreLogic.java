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
    public List<Message> selectMessageList(int userNo) {
        return session.selectList("MessageMapper.selectMessageList",userNo);
    }
}
