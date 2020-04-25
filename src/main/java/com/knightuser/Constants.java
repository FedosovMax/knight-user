package com.knightuser;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    public static final String USERS_BASE_URL = "/users";

    public static final String ROLES_BASE_URL = "/roles";

    public static final String AUTHENTICATION_BASE_URL = "/auth";

    public static String buildGetUserByIdBaseUrl(String userId) {
        return USERS_BASE_URL + "/" + userId;
    }

    public static String buildUpdateUserBaseUrl(String userId) {
        return USERS_BASE_URL + "/" + userId;
    }

    public static String buildDeleteUserByIdUrl(String userId) {
        return USERS_BASE_URL + "/" + userId;
    }
}
