package com.kh.mango.message.store;

import com.kh.mango.message.domain.Message;


import java.util.List;

public interface MessageStore {


    Message insertChatRoom(Message message);
}
