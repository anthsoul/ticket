package org.example.infrastructure.adapter;

import org.example.domain.entity.User;
import org.example.infrastructure.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserAdapter {
    public User convertToUser(UserEntity user) {
        return User.builder().id(user.getUId()).build();
    }
}
