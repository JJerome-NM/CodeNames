package com.codenames.models;

import com.codenames.dto.RoomDto;
import com.codenames.enums.PlayerRole;
import com.codenames.enums.GameStatus;
import com.codenames.exception.RoomLimitIsOver;
import com.codenames.utils.IntegerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CodeNamesGame {

    private static final int MIN_ROOM_ID = 100000;

    private static final int MAX_ROOM_ID = 1000000;

    private static final int ROOM_LIMIT = MAX_ROOM_ID - MIN_ROOM_ID;

    private final BannedUsers bannedUsers;

    private final AuthorizedUsers authorizedUsers;

    private final Map<Integer, Room> gameRooms = new HashMap<>();


    public boolean checkAvailabilityRoom(WebSocketSession session, int roomID){
        return this.gameRooms.containsKey(roomID);
    }

    public void addUserToRoom(User user, int roomID){
        this.gameRooms.get(roomID).addUserToRoom(new Player(PlayerRole.SPECTATOR, user));
    }

    public RoomDto getRoomInfo(UserRoomSession roomSession){
        return this.gameRooms.get(roomSession.getRoomID()).getRoomInfo(roomSession.getPlayer());
    }

    public void changeGameStatus(UserRoomSession roomSession, GameStatus status){
        this.gameRooms.get(roomSession.getRoomID()).changeGameStatus(roomSession.getPlayer(), status);
    }

    public void changeUserRoleInRoom(UserRoomSession roomSession, PlayerRole selectedRole){
        this.gameRooms.get(roomSession.getRoomID()).selectRole(roomSession.getPlayer(), selectedRole);
    }

    public void selectWord(UserRoomSession roomSession, int wordID){
        this.gameRooms.get(roomSession.getRoomID()).selectWord(roomSession.getPlayer(), wordID);
    }

    public int createNewRoom(Player roomAdmin){
        if (this.gameRooms.size() > ROOM_LIMIT){
            throw new RoomLimitIsOver("Room limit is over!");
        }
        int newRoomID = this.generateRoomID();

        this.gameRooms.put(newRoomID, new Room(newRoomID, roomAdmin));

        return newRoomID;
    }

    private int generateRoomID(){
        int newRoomId = IntegerUtils.randInt(MIN_ROOM_ID, MAX_ROOM_ID);
        while (this.gameRooms.containsKey(newRoomId)){
            newRoomId++;
        }
        return newRoomId;
    }
}
