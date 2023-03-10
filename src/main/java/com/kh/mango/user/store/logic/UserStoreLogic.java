package com.kh.mango.user.store.logic;

import com.kh.mango.point.domain.PointRecord;
import com.kh.mango.user.domain.*;
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
    public int insertUser(SqlSession session, User user) {
        int result = session.insert("UserMapper.insertUser", user);
        session.insert("UserMapper.insertPoint",user);
        return result;
    }

    @Override
    public User checkUserLogin(SqlSession session, User user) {
        User uOne = session.selectOne("UserMapper.checkUserLogin", user);
        return uOne;
    }

    @Override
    public User findUserId(User user) {
        return sqlSession.selectOne("UserMapper.selectFindId", user);
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

    @Override
    public List<Like> selectMyPageLikes(int userNo) {
        return sqlSession.selectList("UserMapper.selectLikes",userNo);
    }

    @Override
    public List<PointRecord> selectPointRecord(int userNo) {
        return sqlSession.selectList("UserMapper.selectPointRecord",userNo);
    }

    @Override
    public User selectOneByNumber(int userNo) {
        User user = sqlSession.selectOne("UserMapper.selectOneByNumber", userNo);
        return user;
    }
}
