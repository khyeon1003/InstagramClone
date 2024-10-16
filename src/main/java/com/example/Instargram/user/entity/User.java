package com.example.Instargram.user.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;


@Data
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String email;
    @Column
    String password;
    @Column
    String phoneNum;
    @Column
    Boolean gender;
    @Column
    Date birth;
    @Column
    String name;
    @Column
    String nickname;
    @Column
    String webSite;
    @Column
    String introduce;
    @Column
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
