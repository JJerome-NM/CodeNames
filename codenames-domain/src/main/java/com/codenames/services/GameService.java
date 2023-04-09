package com.codenames.services;

import com.codenames.exceptions.RoomLimitIsOver;
import com.codenames.models.for_game.CodeNamesGame;
import com.codenames.models.for_game.User;
import com.codenames.models.for_rooms.Room;
import com.codenames.models.for_game.Player;
import com.codenames.utils.IntegerUtils;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private static final int MIN_ROOM_ID = 100000;

    private static final int MAX_ROOM_ID = 1000000;

    private static final int ROOM_LIMIT = MAX_ROOM_ID - MIN_ROOM_ID;

    public boolean checkAvailabilityRoom(CodeNamesGame codeNamesGame, int roomID){
        return codeNamesGame.getGameRooms().containsKey(roomID);
    }

    public void addUserToBanList(CodeNamesGame codeNamesGame, User user){
        codeNamesGame.getBannedUsers().add(user);
    }

    public boolean checkUserBlocked(CodeNamesGame codeNamesGame, User user){
        return codeNamesGame.getBannedUsers().contains(user);
    }

    public void removeUserFromBanList(CodeNamesGame codeNamesGame, User user){
        codeNamesGame.getBannedUsers().remove(user);
    }

    public int createNewRoom(CodeNamesGame codeNamesGame, Player roomAdmin){
        if (codeNamesGame.getGameRooms().size() > ROOM_LIMIT){
            throw new RoomLimitIsOver("Room limit is over!");
        }
        int newRoomID = generateRoomID(codeNamesGame);

        codeNamesGame.getGameRooms().put(newRoomID, new Room(newRoomID, roomAdmin));

        return newRoomID;
    }

    private int generateRoomID(CodeNamesGame codeNamesGame){
        int newRoomId = IntegerUtils.randInt(MIN_ROOM_ID, MAX_ROOM_ID);
        while (codeNamesGame.getGameRooms().containsKey(newRoomId)){
            newRoomId++;
        }
        return newRoomId;
    }
}
