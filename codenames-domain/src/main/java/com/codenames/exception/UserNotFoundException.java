package com.codenames.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RuntimeException{

    private HttpStatus code;

    public UserNotFoundException(String message, HttpStatus code){
        super(message);
        this.code = code;
    }
}
