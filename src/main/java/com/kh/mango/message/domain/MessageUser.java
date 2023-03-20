package com.kh.mango.message.domain;

import lombok.Data;

@Data
public class MessageUser {
    private int sendUserNo;
    private int receiveUserNo;

    public MessageUser(int sendUserNo, int receiveUserNo) {
        this.sendUserNo = sendUserNo;
        this.receiveUserNo = receiveUserNo;
    }
}
