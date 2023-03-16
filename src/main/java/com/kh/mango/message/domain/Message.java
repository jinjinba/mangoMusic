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
    private String msgContent;
    private Timestamp msgDate;
    private int sendUserNo;
    private int receiveUserNo;

}
