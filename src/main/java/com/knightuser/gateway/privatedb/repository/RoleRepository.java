package com.knightuser.gateway.privatedb.repository;

import com.knightuser.gateway.privatedb.representation.Role;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {

    Optional<Role> findByName(String name);
}
