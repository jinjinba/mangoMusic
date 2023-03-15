package com.kh.mango.follow.store;

import com.kh.mango.follow.domain.Follow;

import java.util.List;

public interface FollowStore {

    int followUser(Follow followUser);


    List<Follow> followingUser(int userNo);
}
