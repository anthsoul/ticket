package org.example.infrastructure.repository.jpa;

import org.example.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryJpa extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByuId(long id);
    Optional<UserEntity> findByuEmail(String username);
}
