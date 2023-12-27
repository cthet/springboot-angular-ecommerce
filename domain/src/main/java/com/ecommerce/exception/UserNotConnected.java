package com.ecommerce.exception;

public class UserNotConnected extends RuntimeException{
    public UserNotConnected(String message) {
        super(message);
    }
}
