package com.kh.mango.follow.store.logic;

import com.kh.mango.follow.domain.Follow;
import com.kh.mango.follow.store.FollowStore;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FollowStoreLogic implements FollowStore {

    @Autowired
    private SqlSessionTemplate sqlSession;


    @Override
    public int followUser(Follow followUser) {
        int result = sqlSession.insert("FollowMapper.insertFollowUser", followUser);
        return result;
    }
}
