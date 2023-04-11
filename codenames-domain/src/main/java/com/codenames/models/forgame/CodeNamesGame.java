package com.codenames.models.forgame;

import com.codenames.models.forooms.Room;
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

    private final List<User> bannedUsers = new ArrayList<>();

    private final Map<Integer, Room> gameRooms = new HashMap<>();


    public Room getGameRoom(int roomID){
        return this.gameRooms.get(roomID);
    }

}
