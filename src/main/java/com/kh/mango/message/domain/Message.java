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
    private int msgNo;
    private int msgRoom;
    private int msgSendNo;
    private Timestamp sendTime;
    private String msgContent;

    private int chatRoomNo;
    private int userNo1;
    private int userNo2;

    private String chatRoomName;


    public Message(int userNo1, int userNo2) {
        this.userNo1 = userNo1;
        this.userNo2 = userNo2;
    }
}
