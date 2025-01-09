package org.example.application.user.mapper;

import org.example.application.user.dto.UserDto;
import org.example.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto convertUserDomainToUserDto(User user) {
        return  UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .image(user.getImage())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().toString())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
