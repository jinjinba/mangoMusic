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
    private int userNo;
    private int userId;
    private String userName;
    private int msgNo;
    private String msgContent;
    private Timestamp msgDate;
    private int msgOtherUser;
    private int msgUser;
    private int msgRoom;

}
