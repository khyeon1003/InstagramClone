package com.example.Instargram.user.service;

import com.example.Instargram.global.dto.result.JwtTokenSet;
import com.example.Instargram.global.dto.result.SingleResult;
import com.example.Instargram.global.exception.CustomException;
import com.example.Instargram.global.exception.ErrorCode;
import com.example.Instargram.global.service.AuthService;
import com.example.Instargram.global.service.ResponseService;
import com.example.Instargram.user.dto.UserJoinRequest;
import com.example.Instargram.user.dto.UserLoginRequest;
import com.example.Instargram.user.entity.User;
import com.example.Instargram.user.mapper.UserMapper;
//import com.example.Instargram.user.repository.UserMemoryRepository;
import com.example.Instargram.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    //private final UserMemoryRepository userRepository;
    private final AuthService authService;
    //회원가입 과정
    //기존에 존재하는 회원인지 체크-> 회원 객체 생성-> 레포에 저장(후 다시 객체 리턴 이때 유저 식별자 id가 딸려온다)-> 토큰 생성-> 리턴
    public SingleResult<JwtTokenSet> join(UserJoinRequest request) {
        //존재 확인
        if(!userRepository.findByEmail(request.email()).isEmpty()){
            throw new CustomException(ErrorCode.USER_ALREADY_EXIST);
            //에러 던질때 throw로 새로운 에러 던진다
        }
        User newUser= UserMapper.from(request);
        //레포에 저장되면서 유저식별자 id가 정의돼서 온다
        newUser=userRepository.save(newUser);
        //jwt 토큰 생성
        JwtTokenSet jwtTokenSet=authService.generateToken(newUser.getId());
        return ResponseService.getSingleResult(jwtTokenSet);
    }

    //로그인 과정
    //이메일 또는 id로 멤버찾고 password체크후 맞으면 토큰 던지기
    //토큰을 받으면 다른 페이지 접근 권한을 줌
    public SingleResult<JwtTokenSet> login(@Valid UserLoginRequest request) {
        //아이디로 조회해서 객체 가져오기
        User user=userRepository.findByEmail(request.email()).orElseThrow(()->new CustomException(ErrorCode.USER_NOT_EXIST));
        if(!user.getPassword().equals(request.password())){
            throw new CustomException(ErrorCode.USER_WRONG_PASSWORD);
        }
        JwtTokenSet jwtTokenSet=authService.generateToken(user.getId());
        return ResponseService.getSingleResult(jwtTokenSet);
    }
}
