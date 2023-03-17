package com.kh.mango.cs.service.logic;

import com.kh.mango.cs.domain.Cs;
import com.kh.mango.cs.domain.CsSearch;
import com.kh.mango.cs.service.CsService;
import com.kh.mango.cs.store.CsStore;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CsServiceImpl implements CsService {
    @Autowired
    private CsStore cStore;
    @Autowired
    private SqlSession session;

    @Override
    public int insertCs(Cs cs) {
        return cStore.insertCs(session, cs);
    }

    @Override
    public int updateNotice(Cs cs) {
        return cStore.updateNotice(session, cs);
    }

    @Override
    public List<Cs> selectNoticeList() {
        return cStore.selectNoticeList(session);
    }

    @Override
    public int deleteNotice(int csNo) {
        return cStore.deleteNotice(session, csNo);
    }

    @Override
    public Cs selectOneByNo(int csNo) {
        return cStore.selectOneByNo(session, csNo);
    }

    @Override
    public List<Cs> selectListByKeyword(CsSearch nSearch) {
        return cStore.selectListByKeyword(session, nSearch);
    }


}
