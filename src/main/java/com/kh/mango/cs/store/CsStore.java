package com.kh.mango.cs.store;

import com.kh.mango.cs.domain.Cs;
import com.kh.mango.cs.domain.Notice;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public interface CsStore {

    /**
     * 공지사항 등록
     *
     * @param session
     * @param cs
     * @return int
     */

    int insertCs(SqlSession session, Cs cs);

    List<Notice> selectNoticeList(SqlSession session);
}
