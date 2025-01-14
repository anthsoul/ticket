package org.example.infrastructure.client;

import lombok.AllArgsConstructor;
import org.example.application.client.FileCloudStorage;
import org.example.application.user.exception.InternalServerError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@AllArgsConstructor
public class S3FileCloudStorage {
//    private final AmazonS3 s3Client;
//    private final Logger logger = LoggerFactory.getLogger(S3FileCloudStorage.class);
//    @Override
//    public void uploadFile(String bucket, String fileName, MultipartFile file) {
//        try {
//            ObjectMetadata metadata =  new ObjectMetadata();
//            metadata.setContentType(file.getContentType());
//            metadata.setContentLength(file.getSize());
//
//            PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, fileName,file.getInputStream(), metadata);
//            s3Client.putObject(putObjectRequest);
//        } catch (Exception e) {
//            logger.error("Fail to upload file {} with error {} ", fileName, e.getMessage());
//            throw new InternalServerError("Error to put file to s3");
//        }
//    }
//
//    @Override
//    public void deleteFile(String bucket, String fileName) {
//        try {
//            DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucket, fileName);
//            s3Client.deleteObject(deleteObjectRequest);
//        } catch (Exception e) {
//            logger.error("Fail to delete file {} with error {}", fileName, e.getMessage());
//        }
//    }
//
//    @Override
//    public String getFileUrl(String bucket,String fileName) {
//        return  "https://" + bucket + ".s3.amazonaws.com/" + fileName;
//    }
}
