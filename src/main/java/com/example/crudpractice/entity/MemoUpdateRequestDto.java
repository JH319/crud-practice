package com.example.crudpractice.entity;

public class MemoUpdateRequestDto {

    private String title;
    private String content;

    public MemoUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
