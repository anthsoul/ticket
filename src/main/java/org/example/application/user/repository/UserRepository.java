package org.example.application.user.repository;

import org.example.domain.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<User> findUserById(long id);

    Optional<User> findUserByUsername(String username);

    User saveUser(User user);

    void deleteUser(long id);
}
