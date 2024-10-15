package com.example.Instargram.user.mapper;

import com.example.Instargram.user.dto.UserJoinRequest;
import com.example.Instargram.user.entity.User;

public class UserMapper {
    public static User from(UserJoinRequest request){
        return User.builder()
                .email(request.email())
                .password(request.password())
                .phoneNum(request.phoneNum())
                .build();
    }
}
