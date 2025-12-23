package com.example.crudpractice.entity;

public class MemoCreateResponseDto {

    private Long id;
    private String writer;
    private String title;
    private String content;

    public MemoCreateResponseDto(Long id, String writer, String title, String content) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
