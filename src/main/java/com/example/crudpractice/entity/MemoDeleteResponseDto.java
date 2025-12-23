package com.example.crudpractice.entity;

public class MemoDeleteResponseDto {

    private Long id;

    public MemoDeleteResponseDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
