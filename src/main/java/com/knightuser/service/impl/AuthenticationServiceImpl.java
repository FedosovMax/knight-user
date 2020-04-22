package com.knightuser.service.impl;

import com.knightuser.domain.UserVO;
import com.knightuser.rest.request.AuthenticationRequestDto;
import com.knightuser.rest.response.AuthenticationResponseDto;
import com.knightuser.security.jwt.JwtTokenProvider;
import com.knightuser.service.AuthenticationService;
import com.knightuser.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Override
    public AuthenticationResponseDto login(AuthenticationRequestDto requestDto) {
        String username = requestDto.getUsername();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
        UserVO userVO = userService.findByUsername(username);
        String token = jwtTokenProvider.createToken(userVO);
        return AuthenticationResponseDto.builder().username(username).token(token).build();
    }
}
