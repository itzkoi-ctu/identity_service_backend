package com.koi.identity_service.repository;

import com.koi.identity_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByUserName(String userName);
    Optional<User> findByuserName(String userName);

}
