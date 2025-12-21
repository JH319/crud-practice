package com.example.crudpractice.dto;

public class ApiResponse<T> {

    // 속성
    private String message;
    private Integer status;
    private T data;

    // 생성자
    public ApiResponse(String message, Integer status, T data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    // 기능
    public String getMessage() {
        return message;
    }

    public Integer getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }
}
