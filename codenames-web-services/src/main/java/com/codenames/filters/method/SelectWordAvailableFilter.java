package com.codenames.filters.method;

import com.codenames.enums.GameTurn;
import com.codenames.models.forgame.AuthorizedUsers;
import com.codenames.models.forgame.CodeNamesGame;
import com.codenames.models.forgame.Player;
import com.codenames.models.forooms.Room;
import com.codenames.services.TeamService;
import com.jjerome.annotations.FilteringOrder;
import com.jjerome.dto.Request;
import com.jjerome.filters.SocketMethodFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@FilteringOrder(order = 2)
public class SelectWordAvailableFilter implements SocketMethodFilter {

    private final CodeNamesGame codeNamesGame;

    private final AuthorizedUsers authorizedUsers;

    private final TeamService teamService;

    @Override
    public boolean doFilter(WebSocketSession session, TextMessage message, Request<?> request) {
        final Room room = codeNamesGame.getGameRoom(authorizedUsers.getUserRoomSession(session.getId()).getRoomID());
        final Player player = authorizedUsers.getUserRoomSession(session.getId()).getPlayer();
        final Map<Integer, List<Player>> selectedWords = room.getSelectedWords();
        final GameTurn gameTurn = room.getGameTurn();
        final int wordID = (int) request.getRequestBody();

        if (gameTurn.checkUserTurn(player.getPlayerRole())){
            if (selectedWords.containsKey(wordID)){
                selectedWords.get(wordID).add(player);
            } else {
                selectedWords.put(wordID, List.of(player));
            }

            return gameTurn == GameTurn.BLUE_PLAYERS
                    && teamService.compareTeamPlayers(room.getBlueTeam(), selectedWords.get(wordID))
                    || gameTurn == GameTurn.YELLOW_PLAYERS
                    && teamService.compareTeamPlayers(room.getYellowTeam(), selectedWords.get(wordID));
        }
        return false;
    }
}
