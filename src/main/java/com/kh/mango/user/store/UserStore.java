package com.kh.mango.user.store;

import com.kh.mango.user.domain.Mypage;
import com.kh.mango.user.domain.User;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public interface UserStore {

    User test();

    // 회원가입
    int insertUser(SqlSession session, User user);

    //    로그인
    User checkUserLogin(SqlSession session, User user);

    List<User> selectMember();

    Mypage selectMypageUser(int user);

    List<User> searchUser(String searchValue);

}
