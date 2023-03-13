package com.kh.mango.message.store;

import com.kh.mango.message.domain.Message;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MessageStore {
    List<Message> selectMessageList(int userNo);
}
