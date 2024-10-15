package com.example.Instargram.global.config;

import com.example.Instargram.global.jwt.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration//설정 클래스라는 빈으로 등록하는 것이다
public class WebConfig implements WebMvcConfigurer {

    private final JwtInterceptor jwtInterceptor;

    public WebConfig(JwtInterceptor jwtInterceptor) {
        this.jwtInterceptor = jwtInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/api/post/**");
    }
    //인터셉터 등록 -> api/psot라는 url요청에 jwt를 헤더에 붙여서 보낸다
    //user요청에는 붙일 필요가 없다
}
