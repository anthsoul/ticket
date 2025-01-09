package org.example.application.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterRequestDTO {
    private String name;
    private MultipartFile file;
    private String username;
    private String email;
    private String password;
    private String role;
    private String phoneNumber;
}
