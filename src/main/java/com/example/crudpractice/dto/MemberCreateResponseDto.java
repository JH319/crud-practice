package com.example.crudpractice.dto;

public class MemberCreateResponseDto {

    private Long id;

    public MemberCreateResponseDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
