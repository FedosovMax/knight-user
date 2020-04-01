package com.knightuser.service;

import com.knightuser.domain.UserVO;
import java.util.List;

public interface UserService {

    UserVO save(UserVO userVO);

    List<UserVO> findAll();

    UserVO findById(String userId);

    UserVO updateUser(String userId, UserVO userVO);

    void deleteById(String userId);
}
