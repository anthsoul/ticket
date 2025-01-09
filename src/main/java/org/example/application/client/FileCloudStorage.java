package org.example.application.client;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileCloudStorage {
    void uploadFile(String bucket,String fileName, MultipartFile file);
    void deleteFile(String bucket,String fileName);
    String getFileUrl(String bucket,String fileName);
}
