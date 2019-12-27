package com.knightuser.app.gateway.privatedb.repository;

import com.knightuser.app.gateway.privatedb.representation.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
