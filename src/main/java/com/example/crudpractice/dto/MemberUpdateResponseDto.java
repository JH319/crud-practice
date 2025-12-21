package com.example.crudpractice.dto;

public class MemberUpdateResponseDto {

    // 속성
    private Long id;

    // 생성자
    public MemberUpdateResponseDto(Long id) {
        this.id = id;
    }

    // 기능
    public Long getId() {
        return id;
    }
}
