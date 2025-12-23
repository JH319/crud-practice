package com.example.crudpractice.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
@Table(name = "memos")
public class Memo {

    // 속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "writer", nullable = false, length = 30)
    private String writer;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "content", nullable = false, length = 100)
    private String content;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    // 생성자
    // JPA가 사용하는 생성자
    protected Memo() {}

    public Memo(String writer, String title, String content) {
        this.writer = writer;
        this.title = title;
        this.content = content;
    }

    // 기능
    // 메모 수정
    public Memo update(String newTitle, String newContent) {
        this.title = newTitle;
        this.content = newContent;
        return this;
    }

    // 소프트 딜리트
    public void softDelete() {
        this.isDeleted = true;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
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
