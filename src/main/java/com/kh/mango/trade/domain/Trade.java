package com.kh.mango.trade.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Trade {
    private int tradeNo;
    private String tradeSubject;
    private String tradeContent;
    private int userNo;

}
