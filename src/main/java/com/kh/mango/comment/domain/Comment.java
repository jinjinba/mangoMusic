package com.kh.mango.comment.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private int commentNo;
    private int userNo;
    private String commentContent;
    private Timestamp commentDate;
    private Timestamp commentUpdate;
    private int tradeNo;
    private String musicId;

}
