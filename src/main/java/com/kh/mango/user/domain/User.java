package com.kh.mango.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class User {

    private int userNo;

    public User(int userNo, String userId, String userPw, String userName, String userEmail, String userGender, int userType, Timestamp userDate, String userFilename, String userFilepath, String userProfileLetter) {
        this.userNo = userNo;
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userGender = userGender;
        this.userType = userType;
        this.userDate = userDate;
        this.userFilename = userFilename;
        this.userFilepath = userFilepath;
        this.userProfileLetter = userProfileLetter;
    }

    private String userId;
    private String userPw;
    private String userName;
    private String userEmail;
    private String userGender;
    private int userType;
    private Timestamp userDate;
    private String userFilename;
    private String userFilepath;
    private String userProfileLetter;

    public User(String userId, String userPw) {
        this.userId = userId;
        this.userPw = userPw;
    }
}
