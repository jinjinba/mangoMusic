package com.kh.mango.deal.domain;

import lombok.*;

import java.sql.Timestamp;

@Data
public class Deal {
    private int TradeNo;
    private String dealType;
    private Timestamp dealDate;
    private int buyerNo;
    private int sellerNo;
    private int dealPrice;
}
