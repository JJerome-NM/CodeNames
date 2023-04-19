package com.codenames.models.game;

import com.codenames.models.room.Room;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@RequiredArgsConstructor
@Getter
public class CodeNamesGame {

    // TODO: 18.04.2023  bannedUsers - will be taken from the database at the moment it is a temporary variable

    private final List<User> bannedUsers = new ArrayList<>();

    private final Map<Integer, Room> gameRooms = new HashMap<>();


    public Room getGameRoom(int roomID){
        return this.gameRooms.get(roomID);
    }

}
