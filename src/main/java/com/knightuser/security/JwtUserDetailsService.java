package com.knightuser.security;

import com.knightuser.domain.UserVO;
import com.knightuser.security.jwt.JwtUserFactory;
import com.knightuser.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVO userVO = userService.findByUsername(username);
        return JwtUserFactory.create(userVO);
    }
}
