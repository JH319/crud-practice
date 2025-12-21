package com.example.crudpractice.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MemberCreateRequestDto {

    // 속성
    private String name;

    // 생성자
    // 안 적어도 내부적으로 동작은 하는데,
    // Json 만들 때 사용하는 생성자인 걸 알기 쉬움
    @JsonCreator
    public MemberCreateRequestDto(@JsonProperty("name") String name) {
        this.name = name;
    }

    // 기능
    public String getName() {
        return name;
    }
}
