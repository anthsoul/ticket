package org.example.controller.file;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class HLSController {

    private final String VIDEO_DIR = "C:/workspace/java/ticket/src/main/resources/hls/";

    @GetMapping("/hls/{fileName:.+}")
    public ResponseEntity<Resource> streamVideo(@PathVariable String fileName) throws MalformedURLException {
        Path videoPath = Paths.get(VIDEO_DIR).resolve(fileName);
        Resource resource = new UrlResource(videoPath.toUri());

        if (resource.exists() || resource.isReadable()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
