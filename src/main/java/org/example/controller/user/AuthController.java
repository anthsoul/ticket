package org.example.controller.user;

import lombok.AllArgsConstructor;
import org.example.application.user.dto.LoginRequestDto;
import org.example.application.user.dto.LogoutRequest;
import org.example.application.user.dto.RegisterRequestDTO;
import org.example.application.user.service.AuthService;
import org.example.controller.dto.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    private ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {
        return  ResponseEntity.ok(ResponseBody.builder()
                .data(authService.login(loginRequestDto))
                .message("Login success!"));
    }

    @PostMapping(value = "/register", consumes = {"multipart/form-data"})
    public ResponseEntity<?> register(
            @RequestParam String name,
            @RequestParam MultipartFile file,
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String role,
            @RequestParam String phoneNumber
    ) {
        RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO();
        registerRequestDTO.setName(name);
        registerRequestDTO.setFile(file);
        registerRequestDTO.setUsername(username);
        registerRequestDTO.setEmail(email);
        registerRequestDTO.setPassword(password);
        registerRequestDTO.setRole(role);
        registerRequestDTO.setPhoneNumber(phoneNumber);

        return ResponseEntity.ok(
                ResponseBody.builder()
                        .data(authService.register(registerRequestDTO))
                        .message("Register success!")
                        .build()
        );
    }

    @PostMapping("/logout")
    private ResponseEntity<?> logout(@RequestBody LogoutRequest logoutRequest) {
        authService.logout(logoutRequest);
        return  ResponseEntity.ok(ResponseBody.builder()
                .data(null)
                .message("Logout success!")
                .build());
    }
}
