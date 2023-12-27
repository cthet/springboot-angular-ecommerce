package com.ecommerce.exception;

public class ErrorInRequest extends RuntimeException{

    public ErrorInRequest(String message) {
        super(message);
    }
}
