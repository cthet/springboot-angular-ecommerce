package com.ecommerce.exception;

public class EmailAlreadyExists extends RuntimeException{
    public EmailAlreadyExists(String message) {
        super(message);
    }
}
