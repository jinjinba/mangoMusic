package com.kh.mango.user.store;

import com.kh.mango.user.domain.*;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public interface UserStore {



    MyPage selectMyPageUser(int user);

    //    로그인
    User checkUserLogin(SqlSession session, User user);

    // 회원가입
    int insertUser(SqlSession session, User user);


    List<User> selectMember();


    List<User> searchUser(String searchValue);

    User selectOneByNumber(int userNo);
    List<MyPageFollow> selectMyPageFollower(int userNo);

    List<MyPageDeals> selectMyPageDeals(int userNo);

    List<Like> selectMyPageLikes(int userNo);
}
