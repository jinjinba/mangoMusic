package com.kh.mango.user.service;

import com.kh.mango.user.domain.Mypage;
import com.kh.mango.user.domain.User;

import java.util.List;

public interface UserService {

    User test();

    int insertUser(User user);

    User checkUserLogin(User user);

    List<User> selectMember();

    Mypage mypageInfo(User user);

    List<User> searchUser(String searchValue);
}
