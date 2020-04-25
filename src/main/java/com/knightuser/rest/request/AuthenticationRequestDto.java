package com.knightuser.rest.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AuthenticationRequestDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
