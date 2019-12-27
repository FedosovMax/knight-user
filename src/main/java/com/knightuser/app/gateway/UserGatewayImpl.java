package com.knightuser.app.gateway;

import static java.util.stream.Collectors.toList;

import com.knightuser.app.domain.UserVO;
import com.knightuser.app.gateway.privatedb.mapper.UserMapper;
import com.knightuser.app.gateway.privatedb.repository.UserRepository;
import com.knightuser.app.gateway.privatedb.representation.User;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserGatewayImpl implements UserGateway {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserVO save(UserVO userVO) {
        User user = userRepository.save(userMapper.toUser(userVO));
        return userMapper.toUserVO(user);
    }

    @Override
    public List<UserVO> findAll() {
        return userRepository.findAll().stream().map(userMapper::toUserVO).collect(toList());
    }

    @Override
    public Optional<UserVO> findById(long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void deleteById(long userId) {
        userRepository.deleteById(userId);
    }
}
