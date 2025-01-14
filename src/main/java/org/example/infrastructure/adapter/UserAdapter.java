package org.example.infrastructure.adapter;

import org.example.domain.entity.User;
import org.example.infrastructure.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserAdapter {
    public User convertToUser(UserEntity user) {
        return User.builder().id(user.getUId()).build();
    }
    public static UserEntity convertToUserEntity(User user){
        return UserEntity.builder()
                .uName(user.getName())
                .uRole(user.getRole().toString())
                .uEmail(user.getEmail())
                .uImage(user.getImage())
                .uUsername(user.getUsername())
                .uPassword(user.getPassword())
                .uPhoneNumber(user.getPhoneNumber())
                .build();
    };
}
