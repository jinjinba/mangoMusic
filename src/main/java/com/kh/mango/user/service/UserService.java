package com.kh.mango.user.service;

import com.kh.mango.user.domain.*;

import java.util.List;

public interface UserService {

    User test();

    List<User> selectMember();

    MyPage myPageInfo(int userNo);

    int insertUser(User user);

    User checkUserLogin(User user);

    List<User> searchUser(String searchValue);

    List<MyPageFollow> myPageFollow(int userNo);

    List<MyPageDeals> myPageDeals(int userNo);

    List<Like> myPageLikes(int userNo);
}
