package com.knightuser.rest.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class RoleRequestDto {

    @NotBlank
    private String name;
}
