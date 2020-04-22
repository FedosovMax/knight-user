package com.knightuser.service.impl;

import com.knightuser.domain.RoleVO;
import com.knightuser.domain.UserVO;
import com.knightuser.exception.UserNotFoundException;
import com.knightuser.gateway.UserGateway;
import com.knightuser.service.RoleService;
import com.knightuser.service.UserService;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserGateway userGateway;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserVO save(UserVO userVO) {
        RoleVO roleUser = roleService.findByName("ROLE_USER");
        userVO.setRoles(List.of(roleUser));
        userVO.setPassword(passwordEncoder.encode(userVO.getPassword()));
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
    public UserVO findByUsername(String username) {
        return userGateway.findByLogin(username)
            .orElseThrow(() -> new UsernameNotFoundException(String.format("Can't find user with username: %s",
                                                                           username)));
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
