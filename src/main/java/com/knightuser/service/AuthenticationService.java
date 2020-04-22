package com.knightuser.service;

import com.knightuser.rest.request.AuthenticationRequestDto;
import com.knightuser.rest.response.AuthenticationResponseDto;

public interface AuthenticationService {

    AuthenticationResponseDto login(AuthenticationRequestDto requestDto);
}
