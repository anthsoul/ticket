package org.example.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonS3Config {
////    @Value("${cloud.aws.credentials.accessKey}")
//    private static final String accessKey = "Test";
//
////    @Value("${cloud.aws.credentials.secretKey}")
//    private static final String accessSecret = "Test";
//
////    @Value("${cloud.aws.region.static}")
//    private static final String region = "Test";
//
//    @Bean
//    public AmazonS3 s3Client() {
//        AWSCredentials credentials = new BasicAWSCredentials(accessKey, accessSecret);
//
//        return AmazonS3ClientBuilder.standard()
//                .withCredentials(new AWSStaticCredentialsProvider(credentials))
//                .withRegion(region)
//                .build();
//    }
}