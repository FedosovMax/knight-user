package com.knightuser.gateway;

import static java.util.stream.Collectors.toList;

import com.knightuser.domain.UserVO;
import com.knightuser.gateway.privatedb.mapper.UserMapper;
import com.knightuser.gateway.privatedb.repository.UserRepository;
import com.knightuser.gateway.privatedb.representation.User;
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
    public Optional<UserVO> findById(String userId) {
        return userRepository.findById(userId).map(userMapper::toUserVO);
    }

    @Override
    public void deleteById(String userId) {
        userRepository.deleteById(userId);
    }
}
