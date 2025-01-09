package org.example.application.user.service;

import lombok.AllArgsConstructor;
import org.example.application.user.exception.ResourceNotFoundException;
import org.example.application.user.mapper.UserMapper;
import org.example.application.user.dto.UserDto;
import org.example.application.user.repository.UserRepository;
import org.example.domain.entity.Page;
import org.example.domain.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto findUserById(long id) {
        Optional<User> user = userRepository.findUserById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }
        return userMapper.convertUserDomainToUserDto(user.get());
    }

    public User findUserByUserName(String username) {
        Optional<User> user = userRepository.findUserByUsername(username);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }
        return user.get();
    }

    public UserDto findUserByUsername(String username) {
        return null;
    }

    public Page<UserDto> findUserByName() {
        return null;
    }
}
