package org.example.application.user.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto{
    private long id;
    private String name;
    private String image;
    private String username;
    private String email;
    private String role;
    private String phoneNumber;
}
