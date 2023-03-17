package com.kh.mango.cs.service;

import com.kh.mango.cs.domain.Cs;
import com.kh.mango.cs.domain.CsSearch;

import java.util.List;

public interface CsService {

    /**
     * 공지사항 등록
     * @param cs
     * @return int
     */
    int insertCs(Cs cs);

    /**
     * Q&A 등록
     * @param cs
     * @return int
     */
    int insertQna(Cs cs);

    /**
     * 공지사항 수정
     * @param cs
     * @return int
     */
    int updateNotice(Cs cs);

    /**
     * Q&A 수정
     * @param cs
     * @return int
     */
    int updateQna(Cs cs);


    /**
     * 공지사항 리스트
     *
     * @return List<Cs>
     */
    List<Cs> selectNoticeList();

    /**
     * Q&A 리스트
     * @return
     */
    List<Cs> selectQnaList();

    // 공지사항 삭제
    int deleteNotice(int csNo);
    // Q&A 삭제
    int deleteQna(int csNo);

    // 공지사항 상세
    Cs selectOneByNo(int csNo);

    /**
     * Q&A 상세
     * @param csNo
     * @return
     */
    Cs selectQnaOneByNo(int csNo);

    // 공지사항 검색
    List<Cs> selectListByKeyword(CsSearch nSearch);

    // Q&A 검색
    List<Cs> selectQnaListByKeyword(CsSearch qSearch);
}
