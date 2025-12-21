package com.example.crudpractice.dto;

public class PlaylistDeleteResponseDto {

    private Long id;

    public PlaylistDeleteResponseDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
