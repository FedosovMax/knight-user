package com.knightuser.service;

import com.knightuser.domain.RoleVO;

public interface RoleService {

    RoleVO save(RoleVO roleVO);

    RoleVO findByName(String name);
}
