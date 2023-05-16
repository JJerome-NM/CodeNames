package com.codenames.filters.message;

import com.codenames.services.AuthorizedUsersService;
import com.jjerome.filters.SocketMessageFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;


@Component
@RequiredArgsConstructor
public class UserAuthorizedFilter implements SocketMessageFilter {

    private final AuthorizedUsersService authorizedUsers;

    public boolean doFilter(WebSocketSession session, TextMessage message) {
        return authorizedUsers.getUserRoomSession(session.getId()) != null;
    }
}
