package com.codenames.filters.connect;

import com.codenames.models.game.AuthorizedUsers;
import com.codenames.models.game.CodeNamesGame;
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
        return true;
    }
}
