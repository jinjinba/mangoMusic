package com.kh.mango.follow.service;

import com.kh.mango.follow.domain.Follow;

import java.util.List;

public interface FollowService {

    int followUser(Follow followUser);


    List<Follow> followingUser(int userNo);
}
