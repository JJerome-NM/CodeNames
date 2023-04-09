package com.codenames.filters.connect;

import com.codenames.models.for_game.AuthorizedUsers;
import com.codenames.models.for_game.CodeNamesGame;
import com.codenames.models.for_game.User;
import com.codenames.services.GameService;
import com.jjerome.filters.SocketConnectionFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
@RequiredArgsConstructor
public class BannedUserFilter implements SocketConnectionFilter {

    private final CodeNamesGame codeNamesGame;

    private final GameService gameService;

    private final AuthorizedUsers authorizedUsers;


    @Override
    public boolean doFilter(WebSocketSession session) {
        User user = authorizedUsers.getUserRoomSession(session.getId()).getPlayer().user();
        return gameService.checkUserBlocked(codeNamesGame, user);
    }
}
