package com.example.Instargram.global.dto;

import com.example.Instargram.global.dto.result.ResponseState;
import io.swagger.v3.oas.annotations.media.Schema;

public class SuccessResponse<T> {

    @Schema(description = "성공 여부",example = "true")
    private boolean success=true;

    @Schema(description = "상태 코드", example = "1")
    private int code;

    @Schema(description = "응답 메세지", example = " 성공하였습니다.")
    private String msg;

    @Schema(description = "응답 데이터")
    private T result;

    public SuccessResponse(int code, String msg, T result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }
    public static <T> SuccessResponse<T> of(int code, String msg, T result) {
        return new SuccessResponse<>(code, msg, result);
    }

    public static <T> SuccessResponse<T> ok(T result) {
        return of(ResponseState.SUCCESS.getCode(), ResponseState.SUCCESS.getMsg(), result);
    }



}
