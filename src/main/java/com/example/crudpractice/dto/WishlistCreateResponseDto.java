package com.example.crudpractice.dto;

public class WishlistCreateResponseDto {

    private Long id;
    private String name;
    private String description;
    private Long price;

    public WishlistCreateResponseDto(Long id, String name, String description, Long price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
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
