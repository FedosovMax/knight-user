package com.knightuser.gateway.privatedb.mapper;

import com.knightuser.domain.RoleVO;
import com.knightuser.gateway.privatedb.representation.Role;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleVO toRoleVO(Role role);

    Role toRole(RoleVO roleVO);
}
