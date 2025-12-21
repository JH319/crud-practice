package com.example.crudpractice.dto;

public class MemberDetailResponseDto {

    // 속성
    private Long id;
    private String name;

    // 생성자
    public MemberDetailResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // 기능
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
