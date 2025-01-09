package org.example.controller.user;

import lombok.AllArgsConstructor;
import org.example.application.user.dto.LoginRequestDto;
import org.example.application.user.dto.LogoutRequest;
import org.example.application.user.dto.RegisterRequestDTO;
import org.example.application.user.service.AuthService;
import org.example.controller.dto.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/register")
    private ResponseEntity<?> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return  ResponseEntity.ok(ResponseBody.builder()
                .data(authService.register(registerRequestDTO))
                .message("Register success!"));
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
