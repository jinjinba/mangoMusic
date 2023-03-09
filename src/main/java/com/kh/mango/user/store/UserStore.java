package com.kh.mango.user.store;

import com.kh.mango.user.domain.MyPage;
import com.kh.mango.user.domain.MyPageFollow;
import com.kh.mango.user.domain.MyPageDeals;
import com.kh.mango.user.domain.User;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public interface UserStore {

    User test();

    List<User> selectMember();

    MyPage selectMyPageUser(int user);

    //    로그인
    User checkUserLogin(SqlSession session, User user);
    
    // 회원가입
    int insertUser(SqlSession session, User user);

    //    로그인
    User checkUserLogin(SqlSession session, User user);

    List<User> selectMember();

    Mypage selectMypageUser(int user);

    List<User> searchUser(String searchValue);

    List<MyPageFollow> selectMyPageFollower(int userNo);

    List<MyPageDeals> selectMyPageDeals(int userNo);
}
