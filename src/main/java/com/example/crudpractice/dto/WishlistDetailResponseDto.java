package com.example.crudpractice.dto;

public class WishlistDetailResponseDto {

    private Long id;
    private String name;
    private Long price;

    public WishlistDetailResponseDto(Long id, String name, Long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }
}
