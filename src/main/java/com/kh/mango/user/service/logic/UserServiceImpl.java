package com.kh.mango.user.service.logic;

import com.kh.mango.user.domain.Mypage;
import com.kh.mango.user.domain.User;
import com.kh.mango.user.service.UserService;
import com.kh.mango.user.store.UserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserStore userStore;

    @Override
    public User test() {
        return userStore.test();
    }

    @Override
    public Mypage mypageInfo() {
        return userStore.selectMypageUser();
    }

    @Override
    public List<User> selectMember() {
        List<User> userList = userStore.selectMember();
        return userList;
    }


}
