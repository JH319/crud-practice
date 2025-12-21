package com.example.crudpractice.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MemberListResponseDto {

    // 속성
    private Integer count;
    private List<MemberListResponseDto.MemberDto> memberList;

    // 생성자
    public MemberListResponseDto(Integer count, List<MemberListResponseDto.MemberDto> memberList) {
        this.count = count;
        this.memberList = memberList;
    }

    // 기능
    public Integer getCount() {
        return count;
    }

    public List<MemberListResponseDto.MemberDto> getMemberList() {
        return memberList;
    }


    // 내부 클래스
    public static class MemberDto {

        // 속성
        private Long id;
        private String name;

        // 생성자
        @JsonCreator
        public MemberDto(@JsonProperty("id") Long memberId,
                         @JsonProperty("name") String name) {
            this.id = memberId;
            this.name = name;
        }

        // 기능
        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
