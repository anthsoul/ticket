package org.example.infrastructure.repository.implement;

import lombok.AllArgsConstructor;
import org.example.application.user.repository.UserRepository;
import org.example.domain.entity.User;
import org.example.infrastructure.adapter.UserAdapter;
import org.example.infrastructure.entity.UserEntity;
import org.example.infrastructure.repository.jpa.UserRepositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository{
    private final UserRepositoryJpa userRepositoryJpa;
    private final UserAdapter userAdapter;

    @Override
    public Optional<User> findUserById(long id) {
        Optional<UserEntity> user = userRepositoryJpa.findByuId(id);
        return user.map(userAdapter::convertToUser);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        Optional<UserEntity> user = userRepositoryJpa.findByuEmail(username);
        return user.map(userAdapter::convertToUser);
    }

    @Override
    public User saveUser(User user) {
        return userAdapter.convertToUser(userRepositoryJpa.save(UserAdapter.convertToUserEntity(user)));
    }

    @Override
    public void deleteUser(long id) {
        userRepositoryJpa.deleteById(id);
    }
}
