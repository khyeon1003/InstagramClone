package com.example.Instargram.global.jwt;

import com.example.Instargram.global.exception.CustomException;
import com.example.Instargram.global.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

//HandlerInterceptor를 implement함
@Component
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;
    //jwtUtil에 id필드를 추가 하기 위해 가져온다

    public JwtInterceptor(JwtUtil jwtUtil) {this.jwtUtil = jwtUtil; }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        //클라이언트가 요청시 JWT토큰이 포함된다->요청 dto객
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);
            //Bearer를 제외하고 실제 토큰을 추출
            jwtUtil.verify(jwtToken);//JWT검증

            Long id =jwtUtil.getId(jwtToken);//payload에서 id를 따온다
            request.setAttribute("id", id);//여기에 id 필드를 추가한다
            return true;
        }
        throw  new CustomException(ErrorCode.UNAUTHORIZED);//토큰 없거나 유효 하지 않으면 예외 처리
    }

}
