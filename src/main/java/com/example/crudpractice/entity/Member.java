package com.example.crudpractice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;    // 회원 식별자

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "email", nullable = false, length = 200)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "created_at", nullable = false)  // 생성일
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)  // 수정일
    private LocalDateTime updatedAt;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    // JPA가 사용하는 생성자
    // 개발자한테까지 노출시켜 줄 필요가 없기 떄문에 protected 사용
    protected Member() {}

    public Member(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {return password;}

    // 회원 수정
    public Member update(String newMemberName) {
        this.name = newMemberName;
        return this;
    }

    // 소프트 딜리트
    public void softDelete() {
        this.isDeleted = true;
    }

    // JPA에 의존하지 않고 DB에서 설정해주었기 떄문에 안 써도 상관은 없음
    @PrePersist     // 영속화 하기 전에 실행시키는 것
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();   // 데이터가 생성될 때 자동으로 시간을 넣어줌
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
