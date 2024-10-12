package com.example.Instargram.global.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private Exception originException;//원래 발생한 예외를 저장
    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {this.errorCode = errorCode;}

    public CustomException(Exception originException,ErrorCode errorCode) {
        this.originException = originException;
        this.errorCode = errorCode;
    }
}
