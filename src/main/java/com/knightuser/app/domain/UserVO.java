package com.knightuser.app.domain;

import lombok.Data;

@Data
public class UserVO {

    private String id;
    private String login;
    private String password;
    private CharacterVO character;
    private String token;
}
