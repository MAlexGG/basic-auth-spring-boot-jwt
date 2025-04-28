package com.auth.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auth.auth.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

}
