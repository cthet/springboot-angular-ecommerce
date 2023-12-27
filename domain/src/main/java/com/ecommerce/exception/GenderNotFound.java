package com.ecommerce.exception;

public class GenderNotFound extends RuntimeException{
    public GenderNotFound(String message, int genderId) {
        super(message);
    }
}
