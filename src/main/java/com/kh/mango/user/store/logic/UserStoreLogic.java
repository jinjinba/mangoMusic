package com.kh.mango.user.store.logic;

import com.kh.mango.user.domain.MyPage;
import com.kh.mango.user.domain.MyPageFollow;
import com.kh.mango.user.domain.MyPageDeals;
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
    public int insertUser(SqlSession session, User user) {
        int result = session.insert("UserMapper.insertUser", user);
        return result;
    }

    @Override
    public User checkUserLogin(SqlSession session, User user) {
        User uOne = session.selectOne("UserMapper.checkUserLogin", user);
        return uOne;
    }

    @Override
    public List<User> selectMember() {
        List<User> userList = sqlSession.selectList("UserMapper.test");
        return userList;
    }


    @Override
    public MyPage selectMyPageUser(int userNo) {
        return sqlSession.selectOne("UserMapper.selectUserByMyPage",userNo);
    }


    @Override
    public List<User> searchUser(String searchValue) {
        List<User> searchList = sqlSession.selectList("UserMapper.searchUser", searchValue);
        return searchList;
    }

    @Override
    public List<MyPageFollow> selectMyPageFollower(int userNo) {
        return sqlSession.selectList("UserMapper.selectFollowers",userNo);
    }

    @Override
    public List<MyPageDeals> selectMyPageDeals(int userNo) {
        return sqlSession.selectList("UserMapper.selectDeals", userNo);
    }
}
