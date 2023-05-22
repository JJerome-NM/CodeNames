package com.codenames.repository;

import com.codenames.entity.UserAuthRoleEntity;
import com.codenames.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAuthRoleRepository extends JpaRepository<UserAuthRoleEntity, Long> {

    Optional<List<UserAuthRoleEntity>> findUserAuthRoleEntitiesByUser(UserEntity userEntity);
}
