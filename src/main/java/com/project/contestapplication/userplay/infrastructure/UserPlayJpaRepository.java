package com.project.contestapplication.userplay.infrastructure;

import com.project.contestapplication.user.infrastructre.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPlayJpaRepository extends JpaRepository<UserPlayEntity,Long> {

    Optional<UserPlayEntity> findByWriter(UserEntity writer);
}
