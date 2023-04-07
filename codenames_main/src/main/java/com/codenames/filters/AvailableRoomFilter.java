package com.codenames.filters;


import com.codenames.models.CodeNamesGame;
import com.jjerome.annotations.FilteringOrder;
import com.jjerome.dto.Request;
import com.jjerome.filters.SocketMethodFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
@FilteringOrder(order = 2)
public class AvailableRoomFilter implements SocketMethodFilter {

    @Autowired
    private CodeNamesGame codeNamesGame;

    @Override
    public boolean doFilter(WebSocketSession session, TextMessage message, Request<?> request) {
        Integer roomID = (Integer) request.getRequestBody();

        return codeNamesGame.checkAvailabilityRoom(session, roomID);
    }
}
