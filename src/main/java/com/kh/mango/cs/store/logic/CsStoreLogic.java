package com.kh.mango.cs.store.logic;

import com.kh.mango.cs.domain.Cs;
import com.kh.mango.cs.domain.Notice;
import com.kh.mango.cs.domain.nDetail;
import com.kh.mango.cs.store.CsStore;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CsStoreLogic implements CsStore {


    @Override
    public int insertCs(SqlSession session, Cs cs) {
        return session.insert("CsMapper.insertNotice", cs);
    }

    @Override
    public int updateNotice(SqlSession session, Cs cs) {
        return session.update("CsMapper.updateNotice", cs);
    }

    @Override
    public List<Notice> selectNoticeList(SqlSession session) {
        List<Notice> notices = session.selectList("CsMapper.selectNoticeList");
        return notices;
    }

    @Override
    public int deleteNotice(SqlSession session, int csNo) {
        return session.delete("CsMapper.deleteNotice", csNo);
    }

    // 공지사항 상세조회
    @Override
    public nDetail selectOneByNo(SqlSession session, int csNo) {
        return session.selectOne("CsMapper.selectOneByNo",csNo);
    }

}
