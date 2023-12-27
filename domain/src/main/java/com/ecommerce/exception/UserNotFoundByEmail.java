package com.ecommerce.exception;

public class UserNotFoundByEmail extends RuntimeException{

    public UserNotFoundByEmail(String message, String email) {super (message);}
}
