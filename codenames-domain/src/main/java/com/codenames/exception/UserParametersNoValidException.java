package com.codenames.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserParametersNoValidException extends RuntimeException{

    private HttpStatus status;

    public UserParametersNoValidException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }
}
