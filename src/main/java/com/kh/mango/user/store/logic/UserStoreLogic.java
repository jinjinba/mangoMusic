package com.kh.mango.user.store.logic;

import com.kh.mango.user.domain.Mypage;
import com.kh.mango.user.domain.User;
import com.kh.mango.user.store.UserStore;
import org.apache.ibatis.session.SqlSession;
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
    public List<User> selectMember() {
        List<User> userList = sqlSession.selectList("UserMapper.test");
        return userList;
    }

    @Override
    public User checkUserLogin(SqlSession session, User user) {
        User uOne = session.selectOne("UserMapper.checkUserLogin", user);
        return uOne;
    }

    @Override
    public int insertUser(SqlSession session, User user) {
        return 0;
    }


    @Override
    public Mypage selectMypageUser(int userNo) {
        return sqlSession.selectOne("UserMapper.selectUserByMyPage",userNo);
    }


    @Override
    public List<User> searchUser(String searchValue) {
        List<User> searchList = sqlSession.selectList("UserMapper.searchUser", searchValue);
        return searchList;
    }
}
