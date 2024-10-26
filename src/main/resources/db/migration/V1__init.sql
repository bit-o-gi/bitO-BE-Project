CREATE TABLE anniversary (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             writer VARCHAR(255) NOT NULL,
                             title VARCHAR(255) NOT NULL,
                             withpeople VARCHAR(255) NOT NULL,
                             content TEXT NOT NULL,
                             anniversary_date TIMESTAMP NOT NULL,  -- 필드 이름을 일치시킴
                             write_time VARCHAR(255),              -- 엔티티 클래스에 맞게 추가
                             update_time VARCHAR(255),             -- 엔티티 클래스에 맞게 추가
                             email VARCHAR(255) NOT NULL
);
