package com.example.crudpractice.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MemberRegisterRequestDto {

    // 속성
    private String name;
    private String email;
    private String password;

    // 생성자
    // 안 적어도 내부적으로 동작은 하는데,
    // Json 만들 때 사용하는 생성자인 걸 알기 쉬움
    @JsonCreator
    public MemberRegisterRequestDto(
            @JsonProperty("name") String name,
            @JsonProperty("email") String email,
            @JsonProperty("password") String password
    ) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // 기능
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
