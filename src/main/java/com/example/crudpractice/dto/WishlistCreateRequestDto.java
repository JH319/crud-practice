package com.example.crudpractice.dto;

public class WishlistCreateRequestDto {

    private String name;
    private String description;
    private Long price;

    public WishlistCreateRequestDto(String name, String description, Long price) {
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
