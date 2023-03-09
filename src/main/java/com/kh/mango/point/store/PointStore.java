package com.kh.mango.point.store;

import com.kh.mango.point.domain.Point;
import org.apache.ibatis.session.SqlSession;

public interface PointStore {
    int updateAddPoint(SqlSession session, Point point);

    int updateRefundPoint(SqlSession session, Point point);
}
