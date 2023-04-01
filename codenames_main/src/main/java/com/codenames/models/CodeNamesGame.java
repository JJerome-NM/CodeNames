package com.codenames.models;

import exception.RoomLimitIsOver;

import java.util.HashMap;
import java.util.Map;

public class CodeNamesGame {

    private static final int MIN_ROOM_ID = 100000;

    private static final int MAX_ROOM_ID = 1000000;

    private static final int ROOM_LIMIT = MAX_ROOM_ID - MIN_ROOM_ID;


    public static void main(String[] args) {
        new CodeNamesGame().createNewRoom(new User(100, 100, "JJerome"));
    }


    private final Map<Integer, Room> gameRooms = new HashMap<>();

    public void createNewRoom(User roomAdmin){
        if (this.gameRooms.size() > ROOM_LIMIT){
            throw new RoomLimitIsOver("Room limit is over!");
        }
        this.gameRooms.put(generateRoomID(), new Room(roomAdmin));
    }

    private int generateRoomID(){
        int newRoomId = rand(MIN_ROOM_ID, MAX_ROOM_ID);
        while (this.gameRooms.containsKey(newRoomId)){
            newRoomId++;
        }
        return newRoomId;
    }

    private int rand(int min, int max){
        return (int) (Math.random() * (max - min + 1) + min);
    }

}
