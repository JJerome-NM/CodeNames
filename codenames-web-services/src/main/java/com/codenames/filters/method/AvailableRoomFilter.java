package com.codenames.filters.method;


import com.codenames.models.for_game.CodeNamesGame;
import com.codenames.services.GameService;
import com.jjerome.annotations.FilteringOrder;
import com.jjerome.dto.Request;
import com.jjerome.filters.SocketMethodFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
@RequiredArgsConstructor
@FilteringOrder(order = 2)
public class AvailableRoomFilter implements SocketMethodFilter {

    private final CodeNamesGame codeNamesGame;

    private final GameService gameService;

    @Override
    public boolean doFilter(WebSocketSession session, TextMessage message, Request<?> request) {
        Integer roomID = (Integer) request.getRequestBody();

        return gameService.checkAvailabilityRoom(codeNamesGame, roomID);
    }
}
