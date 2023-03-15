package com.kh.mango.cs.service.logic;

import com.kh.mango.cs.domain.Cs;
import com.kh.mango.cs.domain.Notice;
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
    public List<Notice> selectNoticeList() {
        return cStore.selectNoticeList(session);
    }

    @Override
    public int deleteNotice(int csNo) {
        return cStore.deleteNotice(session, csNo);
    }

    @Override
    public Cs selectOneByName(int csNo) {
        return cStore.selectOneByName(session, csNo);
    }
}
