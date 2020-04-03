package com.knightuser;

public class TestConstants {

    public static final String JSON_ROOT = "$";
    public static final String ID_JSON_PATH = ".id";
    public static final String LOGIN_JSON_PATH = ".login";
    public static final String LENGTH_JSON_PATH = ".length()";

    public static String buildIdJsonPath() {
        return JSON_ROOT + ID_JSON_PATH;
    }

    public static String buildLoginJsonPath() {
        return JSON_ROOT + LOGIN_JSON_PATH;
    }

    public static String buildJsonPathToLength() {
        return JSON_ROOT + LENGTH_JSON_PATH;
    }
}
