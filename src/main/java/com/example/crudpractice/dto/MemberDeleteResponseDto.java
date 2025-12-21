package com.example.crudpractice.dto;

public class MemberDeleteResponseDto {

    // 속성
    private Long id;

    // 생성자
    public MemberDeleteResponseDto(Long id) {
        this.id = id;
    }

    // 기능
    public Long getId() {
        return id;
    }
}
