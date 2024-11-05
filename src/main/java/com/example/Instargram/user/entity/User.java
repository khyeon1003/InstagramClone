package com.example.Instargram.user.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;


@Data
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String phoneNum;
    @Column
    private Boolean gender;
    @Column
    private Date birth;
    @Column
    private String name;
    @Column
    private String nickname;
    @Column
    private String webSite;
    @Column
    private String introduce;
    @Column
    private String imageUrl;


    @Builder
    public User(Long id, String email, String password, String phoneNum, Boolean gender, Date birth, String name, String nickname, String webSite, String introduce, String imageUrl) {
        this.id = id;
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
