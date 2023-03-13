package com.kh.mango.user.domain;

import lombok.*;

import java.sql.Timestamp;

@Data
public class User {

    private int userNo;
    private String userId;
    private String userPw;
    private String userName;
    private String userEmail;
    private String userGender;
    private Integer userType;
    private Timestamp userDate;
    private String userFilename;
    private String userFilepath;
    private String userProfileLetter;

    public User() {
    }

    public User(String userId, String userPw) {
        this.userId = userId;
        this.userPw = userPw;
    }

    public User(String userName, String userEmail, String userGender) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userGender = userGender;
    }


    public User(String userId, String userPw, String userName, String userEmail) {
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.userEmail = userEmail;
    }
}

