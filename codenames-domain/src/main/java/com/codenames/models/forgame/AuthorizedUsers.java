package com.codenames.models.forgame;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Data
public class AuthorizedUsers {

    private final Map<String, UserRoomSession> authorizedUsers = new HashMap<>();

    public UserRoomSession getUserRoomSession(String sessionID){
        return authorizedUsers.get(sessionID);
    }

    public boolean checkUserAuthorized(User user){
        return this.authorizedUsers.containsValue(user);
    }

    public void addUserRoomSession(String sessionID, int roomID, User user){
        authorizedUsers.put(sessionID, new UserRoomSession(roomID, new Player(null, user)));
    }

    public void removeUserRoomSession(String sessionID){
        authorizedUsers.remove(sessionID);
    }

    public void setNewRoomID(String sessionID, int newRoomID){
        authorizedUsers.get(sessionID).setRoomID(newRoomID);
    }
}
