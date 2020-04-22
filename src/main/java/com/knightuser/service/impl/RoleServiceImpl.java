package com.knightuser.service.impl;

import com.knightuser.domain.RoleVO;
import com.knightuser.exception.RoleNotFoundException;
import com.knightuser.gateway.RoleGateway;
import com.knightuser.service.RoleService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleGateway roleGateway;

    @Override
    public RoleVO save(RoleVO roleVO) {
        return roleGateway.save(roleVO);
    }

    @Override
    public RoleVO findByName(String name) {
        return roleGateway.findByName(name)
            .orElseThrow(() -> new RoleNotFoundException(String.format("Can't find role with name: %s", name)));
    }
}
