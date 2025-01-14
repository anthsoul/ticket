package org.example.controller.file;

import lombok.AllArgsConstructor;
import org.example.application.client.CloudS3Client;
import org.example.controller.dto.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/file")
public class FileController {
    private final CloudS3Client client;

    @GetMapping("/presigned-url")
    public ResponseEntity<?> getPresignedPutObjectUrl(@RequestParam("fileName") String fileName) {
        return ResponseEntity.ok(
                ResponseBody.builder()
                        .data(client.getPresignedPutObjectUrl(fileName))
                        .message("Create presign url success!")
                        .build()
        );
    }

    @PostMapping("/process-file")
    public ResponseEntity<?> processFile() {
        return  ResponseEntity.ok(
                ResponseBody.builder()
                        .data(null)
                        .message("File was processing!")
                        .build()
        );
    }
}
