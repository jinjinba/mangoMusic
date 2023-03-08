package com.kh.mango.user.service;

import com.kh.mango.user.domain.Mypage;
import com.kh.mango.user.domain.User;

import java.util.List;

public interface UserService {

    User test();

    List<User> selectMember();

    Mypage mypageInfo();

    int insertUser(User user);

    User checkUserLogin(User user);

    List<User> searchUser(String searchValue);
}
