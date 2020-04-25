package com.knightuser.rest.mapper;

import com.knightuser.domain.UserVO;
import com.knightuser.rest.request.UserRequestDto;
import com.knightuser.rest.response.UserResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = RestRoleMapper.class)
public interface RestUserMapper {

    UserVO toUserVO(UserRequestDto userRequestDto);

    UserResponseDto toUserResponseDto(UserVO userVO);
}
