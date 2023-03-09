package com.kh.mango.cs.store;

import com.kh.mango.cs.domain.Cs;
import org.apache.ibatis.session.SqlSession;

public interface CsStore {

    /**
     * 공지사항 등록
     *
     * @param session
     * @param cs
     * @return int
     */
    int insertNotice(SqlSession session, Cs cs);

}
