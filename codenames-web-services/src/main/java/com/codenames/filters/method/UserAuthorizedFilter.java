package com.codenames.filters.method;

import com.codenames.models.game.AuthorizedUsers;
import com.jjerome.annotations.FilteringOrder;
import com.jjerome.dto.Request;
import com.jjerome.filters.SocketMethodFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
@FilteringOrder(order = 1)
@RequiredArgsConstructor
public class UserAuthorizedFilter implements SocketMethodFilter {

    private final AuthorizedUsers authorizedUsers;

    @Override
    public boolean doFilter(WebSocketSession session, TextMessage message, Request<?> request) {
        return true;
    }
}
