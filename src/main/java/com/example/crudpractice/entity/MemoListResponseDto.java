package com.example.crudpractice.entity;


import java.util.List;

public class MemoListResponseDto {

    private Integer count;
    private List<MemoListResponseDto.MemoDto> data;

    public MemoListResponseDto(Integer count, List<MemoListResponseDto.MemoDto> data) {
        this.count = count;
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public List<MemoListResponseDto.MemoDto> getData() {
        return data;
    }

    // 내부클래스
    public static final class MemoDto {
        private Long id;
        private String writer;
        private String title;

        public MemoDto(Long id, String writer, String title) {
            this.id = id;
            this.writer = writer;
            this.title = title;
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
    }
}
