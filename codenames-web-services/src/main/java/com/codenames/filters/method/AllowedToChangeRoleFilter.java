package com.codenames.filters.method;

import com.codenames.enums.PlayerRole;
import com.codenames.models.forgame.AuthorizedUsers;
import com.codenames.models.forgame.CodeNamesGame;
import com.codenames.models.forooms.Room;
import com.codenames.services.RoomService;
import com.jjerome.dto.Request;
import com.jjerome.filters.SocketMethodFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
@RequiredArgsConstructor
public class AllowedToChangeRoleFilter implements SocketMethodFilter {

    private final CodeNamesGame codeNamesGame;

    private final RoomService roomService;

    private final AuthorizedUsers authorizedUsers;


    @Override
    public boolean doFilter(WebSocketSession session, TextMessage message, Request<?> request) {
        PlayerRole playerRole = PlayerRole.valueOf(request.getRequestPath());
        Room room = codeNamesGame.getGameRoom(authorizedUsers.getUserRoomSession(session.getId()).getRoomID());

        return roomService.checkRoleAvailable(room, playerRole);
    }
}
