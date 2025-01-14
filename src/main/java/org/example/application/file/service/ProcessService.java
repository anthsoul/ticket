package org.example.application.file.service;

import lombok.AllArgsConstructor;
import org.example.application.client.CloudS3Client;
import org.example.application.user.exception.InternalServerError;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@AllArgsConstructor
@Service
public class ProcessService {
    private final CloudS3Client client;

    public File convertInputStreamToFile(InputStream inputStream) {
       try {
           File tempFile = File.createTempFile("video", ".mp4");
           try (FileOutputStream fileOutputStream = new FileOutputStream(tempFile)) {
               byte[] buffer = new byte[1024];
               int bytesRead;
               while ((bytesRead = inputStream.read(buffer)) != -1) {
                   fileOutputStream.write(buffer, 0, bytesRead);
               }
           }
           return tempFile;
       } catch (Exception e) {
           throw  new InternalServerError(e.getMessage());
       }
    }

    public void splitVideoToTs(File inputFile) {
        try {
            String outputPattern = "segment%03d.ts";
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "ffmpeg", "-i", inputFile.getAbsolutePath(),
                    "-c:v", "libx264", "-c:a", "aac", "-f", "segment",
                    "-segment_time", "10", "-segment_format", "mpegts", outputPattern);
            Process process = processBuilder.start();
            try {
                process.waitFor();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IOException("FFmpeg process was interrupted", e);
            }
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }

    public void processVideoToHLS(String bucket, String fileName) {
        try {
           File file = convertInputStreamToFile(client.getObject(bucket, fileName));
            splitVideoToTs(file);

        } catch (Exception e) {
            throw  new InternalServerError(e.getMessage());
        }
    }

}
