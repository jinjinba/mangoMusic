package com.kh.mango.cs.store.logic;

import com.kh.mango.cs.domain.Cs;
import com.kh.mango.cs.store.CsStore;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class CsStoreLogic implements CsStore {

    @Override
    public int insertNotice(SqlSession session, Cs cs) {
        int result = session.insert("NoticeMapper.insertNotice", cs);
        return result;
    }
}
