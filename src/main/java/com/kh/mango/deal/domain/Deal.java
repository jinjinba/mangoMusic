package com.kh.mango.deal.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Deal {
    private int TradeNo;
    private int dealType;
    private Timestamp dealDate;
    private int buyerNo;
    private int sellerNo;
}
