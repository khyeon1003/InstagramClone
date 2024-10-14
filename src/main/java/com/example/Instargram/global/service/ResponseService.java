package com.example.Instargram.global.service;

import com.example.Instargram.global.dto.result.ListResult;
import com.example.Instargram.global.dto.result.SingleResult;

import java.util.List;

public class ResponseService {

    // 단일 값을 감싸는 메서드
    public static <T>SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        return result;
    }

    // 리스트 값을 감싸는 메서드
    public static <T>ListResult<T> getListResult(List<T> list) {
        ListResult<T> result = new ListResult<>();
        result.setList(list);
        return result;
    }
}
