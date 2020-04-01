package com.knightuser.service.impl;

import com.knightuser.domain.UserVO;
import com.knightuser.exception.UserNotFoundException;
import com.knightuser.gateway.UserGateway;
import com.knightuser.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserGateway userGateway;

    @Override
    public UserVO save(UserVO userVO) {
        return userGateway.save(userVO);
    }

    @Override
    public List<UserVO> findAll() {
        return userGateway.findAll();
    }

    @Override
    public UserVO findById(String userId) {
        return userGateway.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(String.format("Can't find user with id: %s", userId)));
    }

    @Override
    public UserVO updateUser(String userId, UserVO userVO) {
        UserVO dbUser = findById(userId);

        dbUser.setLogin(userVO.getLogin());
        dbUser.setPassword(userVO.getPassword());

        return userGateway.save(dbUser);
    }

    @Override
    public void deleteById(String userId) {
        userGateway.deleteById(userId);
    }
}
