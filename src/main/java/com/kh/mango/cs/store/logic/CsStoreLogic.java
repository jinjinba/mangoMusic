package com.kh.mango.cs.store.logic;

import com.kh.mango.cs.domain.Cs;
import com.kh.mango.cs.domain.CsSearch;
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
    public int insertQna(SqlSession session, Cs cs) {
        return session.insert("CsMapper.insertQna", cs);
    }

    @Override
    public int updateNotice(SqlSession session, Cs cs) {
        return session.update("CsMapper.updateNotice", cs);
    }

    @Override
    public int updateQna(SqlSession session, Cs cs) {
        return session.update("CsMapper.updateQna", cs);
    }

    @Override
    public List<Cs> selectNoticeList(SqlSession session) {
        List<Cs> notices = session.selectList("CsMapper.selectNoticeList");
        return notices;
    }

    @Override
    public List<Cs> selectQnaList(SqlSession session) {
        return session.selectList("CsMapper.selectQnaList");
    }

    @Override
    public int deleteNotice(SqlSession session, int csNo) {
        return session.delete("CsMapper.deleteNotice", csNo);
    }

    @Override
    public int deleteQna(SqlSession session, int csNo) {
        return session.delete("CsMapper.deleteQna", csNo);
    }
    // 공지사항 상세조회
    @Override
    public Cs selectOneByNo(SqlSession session, int csNo) {
        return session.selectOne("CsMapper.selectOneByNo",csNo);
    }


    @Override
    public Cs selectQnaOneByNo(SqlSession session, int csNo) {
        return session.selectOne("CsMapper.selectQnaOneByNo", csNo);
    }

    // 공지사항 검색
    @Override
    public List<Cs> selectListByKeyword(SqlSession session, CsSearch nSearch) {
        return session.selectList("CsMapper.selectListByKeyword", nSearch);
    }

    @Override
    public List<Cs> selectQnaListByKeyword(SqlSession session, CsSearch qSearch) {
        return session.selectList("CsMapper.selectQnaListByKeyword", qSearch);
    }

}
