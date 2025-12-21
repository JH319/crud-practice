package com.example.crudpractice.dto;

import java.util.List;

public class PlaylistGetAllResponseDto {

    private Integer count;
    private List<PlaylistGetAllResponseDto.PlaylistDto> data;

    public PlaylistGetAllResponseDto(Integer count, List<PlaylistGetAllResponseDto.PlaylistDto> data) {
        this.count = count;
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public List<PlaylistGetAllResponseDto.PlaylistDto> getData() {
        return data;
    }

    // 내부 클래스
    public static class PlaylistDto {

        private Long id;
        private String name;
        private String writer;

        public PlaylistDto(Long id, String name, String writer) {
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
}
