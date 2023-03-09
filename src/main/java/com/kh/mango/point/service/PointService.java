package com.kh.mango.point.service;

import com.kh.mango.point.domain.Point;

public interface PointService {
    int updateAddPoint(Point point);

    int updateRefundPoint(Point point);
}
