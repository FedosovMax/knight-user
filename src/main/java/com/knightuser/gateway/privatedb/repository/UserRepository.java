package com.knightuser.gateway.privatedb.repository;

import com.knightuser.gateway.privatedb.representation.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByLogin(String login);
}
