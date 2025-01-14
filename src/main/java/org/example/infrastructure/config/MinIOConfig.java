package org.example.infrastructure.config;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinIOConfig {
    @Bean
    public MinioClient minioClient() {
        return  MinioClient.builder()
                .endpoint("http://localhost:9000")
                .credentials("70JZxaVGFTORPcu7KcvM", "bStzDIR8iL0uwfOEGvunlE4FD47Af5ybonTsqnxf")
                .build();
    }
}
