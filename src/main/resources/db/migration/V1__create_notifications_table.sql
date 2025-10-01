CREATE TABLE notifications (
    id BIGINT NOT NULL AUTO_INCREMENT,
    message VARCHAR(512) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
