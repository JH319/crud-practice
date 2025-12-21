package com.example.crudpractice.dto;

public class PlaylistCreateResponseDto {

    private Long id;
    private String name;
    private String writer;

    public PlaylistCreateResponseDto(Long id, String name, String writer) {
        this.id = id;
        this.name = name;
        this.writer = writer;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getWriter() {
        return writer;
    }
}
