package com.kh.mango.user.service.logic;

import com.kh.mango.user.domain.Mypage;
import com.kh.mango.user.domain.User;
import com.kh.mango.user.service.UserService;
import com.kh.mango.user.store.UserStore;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserStore userStore;
    @Autowired
    SqlSession session;

    @Override
    public User test() {
        return userStore.test();
    }
    @Override
    public int insertUser(User user) {
        int result = userStore.insertUser(session, user);
        return result;
    }
    @Override
    public User checkUserLogin(User user) {
        User uOne = userStore.checkUserLogin(session, user);
        return uOne;

    }

    @Override
    public List<User> searchUser(String searchValue) {
        List<User> searchList = userStore.searchUser(searchValue);
        return searchList;
    }

    @Override
    public List<User> selectMember() {
        List<User> userList = userStore.selectMember();
        return userList;
    }

    @Override
    public Mypage mypageInfo(User user) {
        return null;
    }

}
