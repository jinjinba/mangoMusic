package com.kh.mango.message.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String userName;
    private int msgNo;
    private String msgContent;
    private Timestamp msgDate;
    private int sendUserNo;
    private int receiveUserNo;
    private int msgRoom;

    public Message(String msgContent, int sendUserNo, int receiveUserNo) {
        this.msgContent = msgContent;
        this.sendUserNo = sendUserNo;
        this.receiveUserNo = receiveUserNo;
    }

    public Message(int sendUserNo, int receiveUserNo) {
        this.sendUserNo = sendUserNo;
        this.receiveUserNo = receiveUserNo;
    }
}
