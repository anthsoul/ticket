package org.example.infrastructure.client;

import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import lombok.AllArgsConstructor;
import org.example.application.client.CloudS3Client;
import org.example.application.user.exception.InternalServerError;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class MinIOCloudStorage implements CloudS3Client {
    private final MinioClient minioClient;
    private static final String BUCKET_VIDEO = "bucket-video";

    public String getPresignedPutObjectUrl(String fileName) {
        try {
            Map<String, String> reqParams = new HashMap<String, String>();
            reqParams.put("response-content-type", "application/json");

            return  minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.PUT)
                            .bucket(BUCKET_VIDEO)
                            .object(fileName)
                            .extraQueryParams(reqParams)
                            .build()
            );
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }

    @Override
    public InputStream getObject(String bucket, String objectName) {
       try {
           return minioClient.getObject(
                   GetObjectArgs.builder()
                           .bucket(bucket)
                           .object(objectName)
                           .build()
           );
       } catch (Exception e) {
           throw  new InternalServerError(e.getMessage());
       }
    }

    @Override
    public void putObject(String bucket, String objectName, InputStream inputStream) {
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucket)
                            .object(objectName)
                            .stream(inputStream, inputStream.available(), -1)
                            .build()
            );
        } catch (Exception e) {
            throw  new InternalServerError(e.getMessage());
        }
    }
}
