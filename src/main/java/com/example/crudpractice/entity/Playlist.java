package com.example.crudpractice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "playlists")
public class Playlist {

    // 속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "song_name", length = 50, nullable = false)
    private String name;

    @Column(name = "song_writer", length = 50, nullable = false)
    private String writer;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    // 생성자
    // JPA가 사용하는 생성자
    protected Playlist() {}

    public Playlist(String name, String writer) {
        this.name = name;
        this.writer = writer;
    }

    // 기능
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getWriter() {
        return writer;
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

    public Playlist update(String updatedPlaylistName, String updatedPlaylistWriter) {
        this.name = updatedPlaylistName;
        this.writer = updatedPlaylistWriter;
        return this;
    }
}
