package com.codenames.exception;

public class RoomLimitIsOver extends RuntimeException {

    public RoomLimitIsOver(String message){
        super(message);
    }
}
