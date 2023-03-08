package com.kh.mango.user.store;

import com.kh.mango.user.domain.Mypage;
import com.kh.mango.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserStore {

    User test();

    List<User> selectMember();

    Mypage selectMypageUser();

    //    로그인
    User checkUserLogin(SqlSession session, User user);
    
    // 회원가입
    int insertUser(SqlSession session, User user);

    List<User> searchUser(String searchValue);

}
