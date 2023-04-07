package com.codenames.filters;

import com.jjerome.annotations.FilteringOrder;
import com.jjerome.dto.Request;
import com.jjerome.filters.SocketMethodFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
@FilteringOrder(order = 1)
public class UserAuthorizedFilter implements SocketMethodFilter {

    @Override
    public boolean doFilter(WebSocketSession session, TextMessage message, Request<?> request) {



        return true;
    }
}
