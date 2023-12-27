package com.ecommerce.model.auth;

public class AuthResponse {

    private UserDto user;

    private String token;


    public UserDto getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }

    public AuthResponse(UserDto user, String token) {
        this.user = user;
        this.token = token;
    }
}
