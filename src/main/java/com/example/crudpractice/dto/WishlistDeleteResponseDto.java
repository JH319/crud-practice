package com.example.crudpractice.dto;

public class WishlistDeleteResponseDto {

    private Long id;

    public WishlistDeleteResponseDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
