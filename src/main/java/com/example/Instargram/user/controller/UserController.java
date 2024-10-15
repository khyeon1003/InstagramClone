package com.example.Instargram.user.controller;

import com.example.Instargram.global.dto.SuccessResponse;
import com.example.Instargram.global.dto.result.JwtTokenSet;
import com.example.Instargram.global.dto.result.SingleResult;
import com.example.Instargram.user.dto.UserJoinRequest;
import com.example.Instargram.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
@Tag(name="User")
@RequiredArgsConstructor
public class UserController {

    //서비스
    private final UserService userService;
    //final을 사용해서 불변성을 보장

    //static은 필요 없다 왜?? static은 모든 신스턴스에서 공유
    //스프링의 의존성 주입을 통해 관리되기 때문에 특정컨트롤러 마다 다른 객체를 주입해야할수도 있음
    //이때 스프링 의존성 관리와 충동할 가능성 존재

    @PostMapping("/join")
    @Operation(summary = "회원 가입")
    public SuccessResponse<SingleResult<JwtTokenSet>> join(@Valid @RequestBody UserJoinRequest request){
        SingleResult<JwtTokenSet> result=userService.join(request);
        return SuccessResponse.ok(result);
        //post 요청에서는 @RequsetBody로 HTTP요청을 자바 객체로 변환하기
    }


}
