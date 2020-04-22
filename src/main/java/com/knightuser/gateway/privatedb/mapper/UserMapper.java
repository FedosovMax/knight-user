package com.knightuser.gateway.privatedb.mapper;

import com.knightuser.domain.UserVO;
import com.knightuser.gateway.privatedb.representation.User;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper {

    UserVO toUserVO(User user);

    User toUser(UserVO userVO);
}
