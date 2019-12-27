package com.knightuser.app.gateway.privatedb.repository;

import com.knightuser.app.gateway.privatedb.representation.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Long> {


}
