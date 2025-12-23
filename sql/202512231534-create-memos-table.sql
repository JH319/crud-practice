CREATE TABLE memos (
                       id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '메모 식별자',
                       writer VARCHAR(30) NOT NULL COMMENT '작성자',
                       title VARCHAR(50) NOT NULL COMMENT '메모 제목',
                       content VARCHAR(100) NOT NULL COMMENT '메모 내용',
                       created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
                       updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
                       is_deleted TINYINT(1) NOT NULL DEFAULT 0 COMMENT '0: 삭제안됨, 1: 삭제됨',
                       PRIMARY KEY (id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;