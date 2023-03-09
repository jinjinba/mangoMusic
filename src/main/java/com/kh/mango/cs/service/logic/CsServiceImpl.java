package com.kh.mango.cs.service.logic;

import com.kh.mango.cs.domain.Cs;
import com.kh.mango.cs.service.CsService;
import com.kh.mango.cs.store.CsStore;
import org.apache.ibatis.session.SqlSession;

public class CsServiceImpl implements CsService {
    private CsStore cStore;
    private SqlSession session;

    @Override
    public int insertNotice(Cs cs) {
        return cStore.insertNotice(session, cs);
    }
}
