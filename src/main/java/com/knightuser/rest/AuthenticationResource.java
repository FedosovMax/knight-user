package com.knightuser.rest;

import static com.knightuser.Constants.AUTHENTICATION_BASE_URL;

import com.knightuser.rest.request.AuthenticationRequestDto;
import com.knightuser.rest.response.AuthenticationResponseDto;
import com.knightuser.service.AuthenticationService;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(AUTHENTICATION_BASE_URL)
@Slf4j
public class AuthenticationResource {

    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<AuthenticationResponseDto> login(@Valid @RequestBody AuthenticationRequestDto requestDto) {
        log.info("Rest request to login : {}", requestDto);
        return ResponseEntity.ok().body(authenticationService.login(requestDto));
    }
}
