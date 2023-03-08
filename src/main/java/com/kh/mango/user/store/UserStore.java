package com.kh.mango.user.store;

import com.kh.mango.user.domain.Mypage;
import com.kh.mango.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserStore {

    User test();

    Mypage selectMypageUser();
}
