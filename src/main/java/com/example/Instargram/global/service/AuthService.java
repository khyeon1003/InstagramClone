package com.example.Instargram.global.service;

import com.example.Instargram.global.dto.result.JwtTokenSet;
import com.example.Instargram.global.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;

    public JwtTokenSet generateToken(Long userIdx){
        String token=jwtUtil.createToken(userIdx);

        JwtTokenSet jwtTokenSet=JwtTokenSet.builder()
                .token(token)
                .build();

        return jwtTokenSet;
    }

}
