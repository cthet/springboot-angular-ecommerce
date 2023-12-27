package com.ecommerce.exception;

import org.springframework.http.HttpStatus;

public class AddressAlreadyExists extends RuntimeException{

    public AddressAlreadyExists(String message) {
        super(message);
    }
}
