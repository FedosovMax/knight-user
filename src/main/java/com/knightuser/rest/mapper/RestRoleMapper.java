package com.knightuser.rest.mapper;

import com.knightuser.domain.RoleVO;
import com.knightuser.rest.request.RoleRequestDto;
import com.knightuser.rest.response.RoleResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestRoleMapper {

    RoleVO toRoleVO(RoleRequestDto roleRequestDto);

    RoleResponseDto toRoleResponseDto(RoleVO roleVO);
}
