package com.codenames.filters.method;

import com.codenames.enums.GameTurn;
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

import java.util.List;

@Component
@FilteringOrder(order = 3)
@RequiredArgsConstructor
public class SkipGameTurnFilter implements SocketMethodFilter {

    private final CodeNamesGame codeNamesGame;

    private final AuthorizedUsers authorizedUsers;

    private final TeamService teamService;

    @Override
    public boolean doFilter(WebSocketSession session, TextMessage message, Request<?> request) {
        final Room room = codeNamesGame.getGameRoom(authorizedUsers.getUserRoomSession(session.getId()).getRoomID());
        final Player player = authorizedUsers.getUserRoomSession(session.getId()).getPlayer();
        final List<Player> selectedSkipTurnPlayers = room.getSelectedSkipTurnPlayers();
        final GameTurn gameTurn = room.getGameTurn();

        if (!selectedSkipTurnPlayers.contains(player)){
            selectedSkipTurnPlayers.add(player);
        }

        return gameTurn.checkUserTurn(player.getPlayerRole())
                && (gameTurn == GameTurn.BLUE_PLAYERS
                    && teamService.compareTeamPlayers(room.getBlueTeam(), selectedSkipTurnPlayers)
                    || gameTurn == GameTurn.YELLOW_PLAYERS
                    && teamService.compareTeamPlayers(room.getYellowTeam(), selectedSkipTurnPlayers));
    }
}
