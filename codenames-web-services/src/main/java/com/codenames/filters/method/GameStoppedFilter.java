package com.codenames.filters.method;

import com.codenames.enums.GameStatus;
import com.codenames.models.game.AuthorizedUsers;
import com.codenames.models.game.CodeNamesGame;
import com.codenames.models.room.Room;
import com.codenames.services.RoomService;
import com.jjerome.annotations.FilteringOrder;
import com.jjerome.dto.Request;
import com.jjerome.filters.SocketMethodFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;


@Component
@FilteringOrder(order = 2)
@RequiredArgsConstructor
public class GameStoppedFilter implements SocketMethodFilter {
    private final CodeNamesGame codeNamesGame;

    private final RoomService roomService;

    private final AuthorizedUsers authorizedUsers;


    @Override
    public boolean doFilter(WebSocketSession session, TextMessage message, Request<?> request) {
        Room room = codeNamesGame.getGameRoom(authorizedUsers.getUserRoomSession(session.getId()).getRoomID());

        return roomService.checkGameStatus(room, GameStatus.STOPPED);
    }
}