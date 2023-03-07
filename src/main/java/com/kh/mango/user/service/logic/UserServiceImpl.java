package com.kh.mango.user.service.logic;

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
}
