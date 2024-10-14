package com.example.Instargram.global.exception;

import com.example.Instargram.global.dto.ErrorResponse;
import com.example.Instargram.global.dto.result.ExceptionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

import static com.example.Instargram.global.exception.ErrorCode.PARAMETER_GRAMMAR_ERROR;
import static com.example.Instargram.global.exception.ErrorCode.PARAMETER_VALIDATION_ERROR;

@RequiredArgsConstructor
@RestControllerAdvice //controllAdvice와 ResponseBody가 결함됨-> 컨트롤러 전역에 발생하는 예외 처리 Json 형식의 응답 반환
public class ExceptionAdvice {

    /**
     * 등록되지 않은 에러 처리 함수
     * 설명: 등록되지 않은 일반적인 예외를 처리합니다.
     * 예외 유형: Exception.class
     * HTTP 상태 코드: 500 INTERNAL_SERVER_ERROR
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorResponse<ExceptionResult.ServerErrorData> handleUntrackedException(Exception e) {
        ExceptionResult.ServerErrorData serverErrorData = ExceptionResult.ServerErrorData.builder()
                .errorClass(e.getClass().toString())
                .errorMessage(e.getMessage())
                .build();
        //ErrorResponse 생성 및 반환
        return ErrorResponse.of(ErrorCode.SERVER_UNTRACKED_ERROR.getErrorCode(), ErrorCode.SERVER_UNTRACKED_ERROR.getMessage(), serverErrorData);
    }

    /**
     * 파라미터 검증 예외
     * 설명: 파라미터 검증 오류를 처리합니다.
     * 예외 유형: MethodArgumentNotValidException.class
     * HTTP 상태 코드: 412 PRECONDITION_FAILED
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public ErrorResponse<List<ExceptionResult.ParameterData>> handleValidationExceptions(MethodArgumentNotValidException e) {
        //오류 리스트 초기화
        List<ExceptionResult.ParameterData> list = new ArrayList<>();
        BindingResult bindingResult = e.getBindingResult();
        //각 필드 오류 처리
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            ExceptionResult.ParameterData parameterData = ExceptionResult.ParameterData.builder()
                    .key(fieldError.getField())
                    .value(fieldError.getRejectedValue() == null ? null : fieldError.getRejectedValue().toString())
                    .reason(fieldError.getDefaultMessage())
                    .build();
            list.add(parameterData);
        }
        //ErrorResponse 반환
        return ErrorResponse.of(PARAMETER_VALIDATION_ERROR.getErrorCode(), PARAMETER_VALIDATION_ERROR.getMessage(), list);
    }


    /**
     * 파라미터 문법 예외
     * 설명: HTTP 메시지 파싱 오류를 처리합니다.
     * 예외 유형: HttpMessageNotReadableException.class
     * HTTP 상태 코드: 412 PRECONDITION_FAILED
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public ErrorResponse<String> handleHttpMessageParsingExceptions(HttpMessageNotReadableException e) {
        //파싱 오류 메세지를 받아 ErrorResponse를 반환
        return ErrorResponse.of(PARAMETER_GRAMMAR_ERROR.getErrorCode(), PARAMETER_GRAMMAR_ERROR.getMessage(), e.getMessage());
    }

    /**
     * 커스텀 예외
     * 설명: 커스텀 예외를 처리합니다.
     * 예외 유형: CustomException.class
     * HTTP 상태 코드: 지정되지 않음 (상태 코드가 설정되어 있지 않으므로, 기본 상태 코드 사용)
     */
    @ExceptionHandler(CustomException.class)
    public ErrorResponse<?> handleCustomException(CustomException e) {
        // CustomException의 에러 코드와 메시지로 ErrorResponse 생성 및 반환
        return ErrorResponse.of(e.getErrorCode().getErrorCode(), e.getErrorCode().getMessage());
    }
}

