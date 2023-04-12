package com.codenames.services;

import com.codenames.exceptions.RoomLimitIsOver;
import com.codenames.models.forgame.CodeNamesGame;
import com.codenames.models.forgame.User;
import com.codenames.models.forooms.Room;
import com.codenames.models.forgame.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GameService {

    private static final int MIN_ROOM_ID = 100000;

    private static final int MAX_ROOM_ID = 1000000;

    private static final int ROOM_LIMIT = MAX_ROOM_ID - MIN_ROOM_ID;

    private static final Logger LOGGER = LoggerFactory.getLogger(GameService.class);

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

        LOGGER.info("Created new room, id - " + newRoomID);

        return newRoomID;
    }

    private int generateRoomID(CodeNamesGame codeNamesGame){
        Random random = new Random();
        int newRoomId = random.nextInt(MAX_ROOM_ID - MIN_ROOM_ID) + MIN_ROOM_ID;
        while (codeNamesGame.getGameRooms().containsKey(newRoomId)){
            newRoomId++;
        }
        return newRoomId;
    }
}
