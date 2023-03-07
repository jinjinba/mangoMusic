package com.kh.mango.user.store;

import com.kh.mango.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserStore {

    User test();

//    멤버등록 store
    public int insertUser(SqlSession session, User user);
}
