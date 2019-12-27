package com.knightuser.app.gateway.privatedb.mapper;

import com.knightuser.app.domain.UserVO;
import com.knightuser.app.gateway.privatedb.representation.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserVO toUserVO(User user);

    User toUser(UserVO userVO);
}
