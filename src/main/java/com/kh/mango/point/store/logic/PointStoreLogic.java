package com.kh.mango.point.store.logic;

import com.kh.mango.point.domain.Point;
import com.kh.mango.point.store.PointStore;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class PointStoreLogic implements PointStore {

    @Override
    public int updateAddPoint(SqlSession session, Point point) {
        session.insert("PointMapper.insertChargePointRecord",point);
        return session.update("PointMapper.chargePoint",point);
    }

    @Override
    public int updateRefundPoint(SqlSession session, Point point) {
        session.insert("PointMapper.insertRefundPointRecord",point);
        return session.update("PointMapper.refundPoint",point);
    }

    @Override
    public Point selectPoint(SqlSession session, int userNo) {
        return session.selectOne("PointMapper.selectPoint",userNo);
    }
}
