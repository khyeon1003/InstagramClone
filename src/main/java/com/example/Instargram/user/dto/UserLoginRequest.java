package com.example.Instargram.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record UserLoginRequest(
        @NotBlank
        @Schema(description = "유저 이메일" ,example = "qwer@gmail.com")
        String email,

        @NotBlank
        @Schema(description = "비밀번호",example = "1234")
        String password
) {
}
