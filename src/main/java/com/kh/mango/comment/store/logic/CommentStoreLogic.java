package com.kh.mango.comment.store.logic;

import com.kh.mango.comment.store.CommnetStore;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentStoreLogic implements CommnetStore {
    @Autowired
    private SqlSession session;
}
