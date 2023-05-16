package com.codenames.filters.connect;

import com.codenames.services.AuthorizedUsersService;
import com.codenames.domain.game.CodeNamesGame;
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

    private final AuthorizedUsersService authorizedUsers;


    @Override
    public boolean doFilter(WebSocketSession session) {
        return true;
    }
}
