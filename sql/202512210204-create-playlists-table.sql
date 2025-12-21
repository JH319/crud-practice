CREATE TABLE playlists (
                           id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '노래 식별자',
                           song_name VARCHAR(50) NOT NULL COMMENT '노래 이름',
                           song_writer VARCHAR(50) NOT NULL COMMENT '작곡가',
                           created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
                           updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
                           is_deleted TINYINT(1) NOT NULL DEFAULT 0 COMMENT '0: 삭제안됨, 1: 삭제됨',
                           PRIMARY KEY (id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;