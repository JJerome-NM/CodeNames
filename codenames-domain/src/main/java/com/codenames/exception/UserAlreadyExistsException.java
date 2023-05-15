package com.codenames.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserAlreadyExistsException extends RuntimeException {

    private final HttpStatus status;

    public UserAlreadyExistsException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }
}
