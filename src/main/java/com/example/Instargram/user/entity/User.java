package com.example.Instargram.user.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    Long id;
    String email;
    String password;
    String phoneNum;
    Boolean gender;
    Date birth;
    String name;
    String nickname;
    String webSite;
    String introduce;
    String imageUrl;

    @Builder
    public User(String email, String password, String phoneNum, Boolean gender, Date birth, String name, String nickname, String webSite, String introduce, String imageUrl) {
        this.email = email;
        this.password = password;
        this.phoneNum = phoneNum;
        this.gender = gender;
        this.birth = birth;
        this.name = name;
        this.nickname = nickname;
        this.webSite = webSite;
        this.introduce = introduce;
        this.imageUrl = imageUrl;
    }
}
