package com.codenames.services;

import com.codenames.dto.RoomDto;
import com.codenames.exceptions.RoomLimitIsOver;
import com.codenames.models.game.CodeNamesGame;
import com.codenames.models.game.User;
import com.codenames.models.room.Room;
import com.codenames.models.game.Player;
import com.codenames.properties.CodeNamesGameProperties;
import com.codenames.properties.DefaultMessagePathProperties;
import com.jjerome.dto.Request;
import com.jjerome.models.MessageSender;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class GameService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameService.class);

    private final Random random = new Random();

    private final CodeNamesGameProperties gameProperties;

    private final MessageSender messageSender;

    private final PlayerService playerService;

    private final RoomService roomService;

    private final DefaultMessagePathProperties defaultMessagePathProperties;

    private int minRoomID;

    private int maxRoomID;

    private int roomLimit;

    @PostConstruct
    public void initialization(){
        this.minRoomID = gameProperties.getMinRoomId();
        this.maxRoomID = gameProperties.getMaxRoomId();

        roomLimit = maxRoomID - minRoomID;
    }


    public boolean checkAvailabilityRoom(CodeNamesGame codeNamesGame, int roomID){
        return codeNamesGame.getGameRooms().containsKey(roomID);
    }

    public Room getRoomByRequest(CodeNamesGame codeNamesGame, Request<?> request){
        return codeNamesGame.getGameRoom(playerService.getPlayerRoomID(request));
    }

    public Room getRoomByID(CodeNamesGame codeNamesGame, int roomID){
        return codeNamesGame.getGameRoom(roomID);
    }

    public void sendNewRoomInfoToPlayer(Request<?> request, Room room, Player player){
        RoomDto roomDto = roomService.getRoomInfo(room, player);
        messageSender.send(request.getSessionID(), defaultMessagePathProperties.getNewRoomInfoPath(), roomDto);
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
        if (codeNamesGame.getGameRooms().size() > roomLimit){
            throw new RoomLimitIsOver("Room limit is over!");
        }
        int newRoomID = generateRoomID(codeNamesGame);

        codeNamesGame.getGameRooms().put(newRoomID, new Room(newRoomID, roomAdmin));

        return newRoomID;
    }

    private int generateRoomID(CodeNamesGame codeNamesGame){
        int newRoomId = random.nextInt(maxRoomID - minRoomID) + minRoomID;
        while (codeNamesGame.getGameRooms().containsKey(newRoomId)){
            newRoomId++;
        }
        return newRoomId;
    }
}
