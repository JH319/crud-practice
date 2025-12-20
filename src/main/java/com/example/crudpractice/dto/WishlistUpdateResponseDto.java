package com.example.crudpractice.dto;

public class WishlistUpdateResponseDto {

    private String name;
    private Long price;

    public WishlistUpdateResponseDto(String name, Long price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }
}
