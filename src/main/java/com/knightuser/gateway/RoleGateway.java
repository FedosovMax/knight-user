package com.knightuser.gateway;

import com.knightuser.domain.RoleVO;

import java.util.Optional;

public interface RoleGateway {

    RoleVO save(RoleVO roleVO);

    Optional<RoleVO> findByName(String name);
}
