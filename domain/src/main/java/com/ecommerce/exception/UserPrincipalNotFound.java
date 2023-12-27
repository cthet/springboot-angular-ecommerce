package com.ecommerce.exception;

public class UserPrincipalNotFound extends RuntimeException{
    public UserPrincipalNotFound(String message) {
        super(message);
    }
}
