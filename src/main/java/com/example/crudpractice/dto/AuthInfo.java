package com.example.crudpractice.dto;

public class AuthInfo {

    private Long memberId;
    private String email;

    public AuthInfo(Long memberId, String email) {
        this.memberId = memberId;
        this.email = email;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getEmail() {
        return email;
    }
}
