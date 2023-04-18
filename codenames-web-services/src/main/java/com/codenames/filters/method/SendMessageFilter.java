package com.codenames.filters.method;

import com.codenames.enums.PlayerRole;
import com.codenames.models.game.AuthorizedUsers;
import com.codenames.models.game.CodeNamesGame;
import com.codenames.models.game.Player;
import com.codenames.models.room.Room;
import com.codenames.services.TeamService;
import com.jjerome.annotations.FilteringOrder;
import com.jjerome.dto.Request;
import com.jjerome.filters.SocketMethodFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
@FilteringOrder(order = 3)
@RequiredArgsConstructor
public class SendMessageFilter implements SocketMethodFilter {

    private final CodeNamesGame codeNamesGame;

    private final AuthorizedUsers authorizedUsers;

    private final TeamService teamService;

    @Override
    public boolean doFilter(WebSocketSession session, TextMessage message, Request<?> request) {
        final Room room = codeNamesGame.getGameRoom(authorizedUsers.getUserRoomSession(session.getId()).getRoomID());
        final Player player = authorizedUsers.getUserRoomSession(session.getId()).getPlayer();

        if (player.getPlayerRole() == PlayerRole.BLUE_MASTER){
            return teamService.checkUserInMaster(room.getBlueTeam(), player);
        } else {
            return teamService.checkUserInMaster(room.getYellowTeam(), player);
        }
    }
}
