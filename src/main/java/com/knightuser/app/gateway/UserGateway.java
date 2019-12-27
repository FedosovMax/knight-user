package com.knightuser.app.gateway;

import com.knightuser.app.domain.UserVO;
import com.knightuser.app.gateway.privatedb.representation.User;
import java.util.List;
import java.util.Optional;

public interface UserGateway {

    UserVO save(UserVO userVO);

    List<UserVO> findAll();

    Optional<UserVO> findById(long userId);

    void deleteById(long userId);
}
