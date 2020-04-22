package com.knightuser.gateway;

import com.knightuser.domain.UserVO;
import java.util.List;
import java.util.Optional;

public interface UserGateway {

    UserVO save(UserVO userVO);

    List<UserVO> findAll();

    Optional<UserVO> findById(String userId);

    Optional<UserVO> findByLogin(String login);

    void deleteById(String userId);
}
