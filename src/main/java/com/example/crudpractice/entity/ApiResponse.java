package com.example.crudpractice.entity;

public class ApiResponse<T>
{
    private String message;
    private Integer status;
    private T data;

    public ApiResponse(String message, Integer status, T data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    // 성공 응답 (200 OK)
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("success", 200, data);
    }

    // 생성 응답 (201 Created)
    public static <T> ApiResponse<T> created(T data) {
        return new ApiResponse<>("created", 201, data);
    }

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
