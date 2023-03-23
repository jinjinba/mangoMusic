package com.kh.mango.trade.service.logic;

import com.kh.mango.comment.domain.Comment;
import com.kh.mango.cs.domain.PageInfo;
import com.kh.mango.trade.domain.Trade;
import com.kh.mango.trade.domain.TradeComment;
import com.kh.mango.trade.domain.TradeSearch;
import com.kh.mango.trade.service.TradeService;
import com.kh.mango.trade.store.TradeStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeServiceImpl implements TradeService {
    @Autowired
    private TradeStore tStore;
    @Override
    public int getTradeListCount() {
        return tStore.getTradeListCount();
    }

    @Override
    public List<Trade> selectTradeList(PageInfo pi) {
        return tStore.selectTradeList(pi);
    }

    @Override
    public Trade selectTradeOneByNo(int tradeNo) {
        return tStore.selectTradeOneByNo(tradeNo);
    }

    @Override
    public List<TradeComment> selectTradeCommentList(int tradeNo) {
        return tStore.selectTradeCommentList(tradeNo);
    }

    @Override
    public int insertTrade(Trade trade) {
        return tStore.insertTrade(trade);
    }

    @Override
    public Trade selectTradeModify(int tradeNo) {
        return tStore.selectTradeModify(tradeNo);
    }

    @Override
    public int updateTrade(Trade trade) {
        return tStore.updateTrade(trade);
    }

    @Override
    public int getListCount(TradeSearch tradeSearch) {
        return tStore.getListCount(tradeSearch);
    }

    @Override
    public List<Trade> selectTradeListByKeyword(PageInfo pi, TradeSearch tradeSearch) {
        return tStore.selectTradeListByKeyword(pi,tradeSearch);
    }


}
