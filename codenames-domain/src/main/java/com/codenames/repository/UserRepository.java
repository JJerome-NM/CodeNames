package com.codenames.repository;

import com.codenames.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    <R> Optional<R> findByLogin(Class<R> type, String nickname);
}
