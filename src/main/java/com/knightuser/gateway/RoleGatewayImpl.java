package com.knightuser.gateway;

import com.knightuser.domain.RoleVO;
import com.knightuser.gateway.privatedb.mapper.RoleMapper;
import com.knightuser.gateway.privatedb.repository.RoleRepository;
import com.knightuser.gateway.privatedb.representation.Role;

import java.util.Optional;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RoleGatewayImpl implements RoleGateway {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public RoleVO save(RoleVO roleVO) {
        Role role = roleRepository.save(roleMapper.toRole(roleVO));
        return roleMapper.toRoleVO(role);
    }

    @Override
    public Optional<RoleVO> findByName(String name) {
        return roleRepository.findByName(name).map(roleMapper::toRoleVO);
    }
}
