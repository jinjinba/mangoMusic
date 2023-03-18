package com.kh.mango.message.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class MessageList {

    private String userName;
    private String userId;
    private int receiveUserNo;
    private int msgRoom;
}
