package com.knightuser.domain;

import java.util.List;

import lombok.Data;

@Data
public class UserVO {

    private String id;
    private String login;
    private String password;
    private List<RoleVO> roles;
}
