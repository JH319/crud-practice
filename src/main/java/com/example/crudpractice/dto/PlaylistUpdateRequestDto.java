package com.example.crudpractice.dto;

public class PlaylistUpdateRequestDto {

    private String name;
    private String writer;

    public PlaylistUpdateRequestDto(String name, String writer) {
        this.name = name;
        this.writer = writer;
    }

    public String getName() {
        return name;
    }

    public String getWriter() {
        return writer;
    }
}
