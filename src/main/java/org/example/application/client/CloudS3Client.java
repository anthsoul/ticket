package org.example.application.client;

import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public interface CloudS3Client {
    public String getPresignedPutObjectUrl(String fileName);
    InputStream getObject(String bucket, String objectName);
    void putObject(String bucket, String objectName, InputStream inputStream);
}
