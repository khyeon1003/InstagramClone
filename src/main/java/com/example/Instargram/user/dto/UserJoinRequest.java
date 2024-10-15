package com.example.Instargram.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;


public record UserJoinRequest(
        @NotBlank
        @Schema(description = "유저 이메일", example = "qwer@gmail.com")
        String email,
        @NotBlank
        @Schema(description = "비밀번호",example = "1234")
        String password,
        @NotBlank
        @Schema(description = "휴대폰 번호",example = "010-1234-5678")
        String phoneNum

        //유저 만들때 이만큼 다 필요하지는 않을듯
        /*
        @Schema(description = "성별(T-남/F-여)", example = "true")
        Boolean gender,
        @Schema(description = "생일",example = "2000-10-03")
        Date birth,
        @Schema(description = "이름", example = "홍길동")
        String name,
        @Schema(description = "닉네임",example = "동서해번쩍")
        String nickname,
        @Schema(description = "웹사이트",example = "www.qwer.com")
        String webSite,
        @Schema(description = "소개", example = "안녕하세요")
        String introduce,
        @Schema(description = "프로필 이미지", example = "www.sdsd.com")
        String imageUrl
        */

) {
}
