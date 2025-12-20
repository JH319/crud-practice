package com.example.crudpractice.dto;

public class WishlistUpdateRequestDto {

    private String name;
    private String description;
    private Long price;

    public WishlistUpdateRequestDto(String name, String description, Long price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getPrice() {
        return price;
    }
}
