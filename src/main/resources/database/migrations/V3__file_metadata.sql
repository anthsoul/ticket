CREATE TABLE file_metadata (
    id BIGINT NOT NULL,
    file_name VARCHAR(255) NOT NULL,
    content_type VARCHAR(255),
    file_size BIGINT,
    upload_time TIMESTAMP,
    bucket_name VARCHAR(255),
    object_key VARCHAR(255),
    checksum VARCHAR(255),
    access_level VARCHAR(255),
    metadata TEXT,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    PRIMARY KEY (id)
);

-- Tạo index trên các trường có thể sử dụng nhiều cho việc tìm kiếm
CREATE INDEX idx_files_bucket_name ON file_metadata (bucket_name);
CREATE INDEX idx_files_object_key ON file_metadata (object_key);
CREATE INDEX idx_files_upload_time ON file_metadata (upload_time);
