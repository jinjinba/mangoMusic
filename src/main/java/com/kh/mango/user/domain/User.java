package com.kh.mango.user.domain;

import com.kh.mango.point.domain.PointRecord;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int userNo;
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

    private PointRecord pointRecord;

    public User(String userId, String userPw) {
        this.userId = userId;
        this.userPw = userPw;
    }

    public User(String userName, String userEmail, String userGender) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userGender = userGender;
    }


    public User(String userId, String userName, String userEmail, String userGender) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userGender = userGender;
    }
}

