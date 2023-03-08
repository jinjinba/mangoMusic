package com.kh.mango.user.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class MyPageDeals {
    private int tradeNo;
    private Timestamp dealDate;
    private int dealType;
    private int dealPrice;
}
