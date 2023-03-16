package com.kh.mango.cs.service;

import com.kh.mango.cs.domain.Cs;
import com.kh.mango.cs.domain.Notice;
import com.kh.mango.cs.domain.nDetail;

import java.util.List;

public interface CsService {

    /**
     * 공지사항 등록
     * @param cs
     * @return int
     */
    int insertCs(Cs cs);

    /**
     * 공지사항 수정
     * @param cs
     * @return int
     */
    int updateNotice(Cs cs);

    /**
     * 공지사항 리스트
     * @return List<Notice>
     */
    List<Notice> selectNoticeList();

    // 공지사항 삭제
    int deleteNotice(int csNo);

    // 공지사항 상세
    nDetail selectOneByNo(int csNo);

}
