package com.knightuser.gateway.privatedb.repository;

import com.knightuser.gateway.privatedb.representation.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
