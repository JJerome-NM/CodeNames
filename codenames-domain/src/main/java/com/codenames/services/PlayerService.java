package com.codenames.services;

import com.codenames.models.game.AuthorizedUsers;
import com.codenames.models.game.Player;
import com.codenames.models.game.User;
import com.codenames.models.game.UserRoomSession;
import com.jjerome.dto.Request;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final AuthorizedUsers authorizedUsers;

    private User getUser(Request<?> request){
        return this.authorizedUsers.getUserRoomSession(request.getSessionID()).getPlayer().getUser();
    }

    private UserRoomSession getUserRoomSession(Request<?> request){
        return this.authorizedUsers.getUserRoomSession(request.getSessionID());
    }

    public int getPlayerRoomID(Request<?> request){
        return this.getUserRoomSession(request).getRoomID();
    }

    public void setPlayerRoomID(Request<?> request, int newRoomID){
        this.getUserRoomSession(request).setRoomID(newRoomID);
    }

    public Player getPlayerByRequest(Request<?> request){
        return this.getUserRoomSession(request).getPlayer();
    }

    public Player getPlayerBuSessionID(String sessionID){
        return this.authorizedUsers.getUserRoomSession(sessionID).getPlayer();
    }
}
