CREATE TABLE wishlists (
                           id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '상품 식별자',
                           name VARCHAR(50) NOT NULL COMMENT '상품 이름',
                           description VARCHAR(100) COMMENT '상품 설명',
                           price BIGINT NOT NULL COMMENT '상품 가격',
                           created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
                           updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
                           is_deleted TINYINT(1) NOT NULL DEFAULT 0 COMMENT '0: 삭제안됨, 1: 삭제됨',
                           PRIMARY KEY (id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;