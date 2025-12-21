package com.example.crudpractice.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MemberUpdateRequestDto {

    // 속성
    private String name;

    // 생성자
    @JsonCreator
    public MemberUpdateRequestDto(@JsonProperty("name")String name) {
        this.name = name;
    }

    // 기능
    public String getName() {
        return name;
    }
}
