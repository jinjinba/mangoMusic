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

    /**
     * 공지사항 리스트
     * @return List<Notice>
     */
    List<Notice> selectNoticeList();

    // 공지사항 삭제
    int deleteNotice(int csNo);

    // 공지사항 상세
    Cs selectOneByName(int csNo);

}
