package com.example.crudpractice.dto;

import java.util.List;

public class WishlistAllResponseDto {

    private Integer count;
    private List<WishlistAllResponseDto.WishlistDto> wishlist; // 내부 클래스 만들고 수정

    public WishlistAllResponseDto(Integer count, List<WishlistAllResponseDto.WishlistDto> wishlist) {
        this.count = count;
        this.wishlist = wishlist;
    }

    public Integer getCount() {
        return count;
    }

    public List getWishlist() {
        return wishlist;
    }

    // ----------------------------------------------------------------

    // 내부 클래스
    public static class WishlistDto {
        private Long id;
        private String name;
        private Long price;

        public WishlistDto(Long id, String name, Long price) {
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
}
