package com.codenames.models.game;

import com.codenames.services.PlayerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Data
@RequiredArgsConstructor
public class AuthorizedUsers {

    // TODO: 18.04.2023  This class will be reworked
    
    private final Map<String, UserRoomSession> authorizedUsers = new HashMap<>();

    public UserRoomSession getUserRoomSession(String sessionID){
        return authorizedUsers.get(sessionID);
    }

    public boolean checkUserAuthorized(User user){
        return this.authorizedUsers.containsValue(user);
    }

    public void addUserRoomSession(String sessionID, int roomID, User user){
        authorizedUsers.put(sessionID, new UserRoomSession(roomID, new Player(sessionID, null, user)));
    }

    public void removeUserRoomSession(Player player){
        authorizedUsers.remove(player.getWsSessionId());
        player.setWsSessionId(null);
    }

    public void setNewRoomID(String sessionID, int newRoomID){
        authorizedUsers.get(sessionID).setRoomID(newRoomID);
    }
}
