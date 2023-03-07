package com.kh.mango.user.store.logic;

import com.kh.mango.user.domain.Mypage;
import com.kh.mango.user.domain.User;
import com.kh.mango.user.store.UserStore;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserStoreLogic implements UserStore {

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public User test() {
        return sqlSession.selectOne("UserMapper.test");
    }

    @Override
    public Mypage selectMypageUser() {
        return sqlSession.selectOne("UserMapper.selectUser");
    }


}
