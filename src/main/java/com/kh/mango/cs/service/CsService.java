package com.kh.mango.cs.service;

import com.kh.mango.cs.domain.Cs;
import com.kh.mango.cs.domain.Notice;

import java.util.List;

public interface CsService {

    /**
     * 공지사항 등록
     * @param cs
     * @return int
     */
    int insertCs(Cs cs);

    List<Notice> selectNoticeList();
}
