package com.codenames.filters.method;

import com.codenames.models.game.CodeNamesGame;
import com.codenames.models.game.Player;
import com.codenames.models.room.Room;
import com.codenames.services.PlayerService;
import com.jjerome.annotations.FilteringOrder;
import com.jjerome.dto.Request;
import com.jjerome.filters.SocketMethodFilter;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;


@Component
@RequiredArgsConstructor
@FilteringOrder(order = 1)
public class UserIsRoomAdminFilter implements SocketMethodFilter {

    private final PlayerService playerService;

    private final CodeNamesGame codeNamesGame;

    @Override
    public boolean doFilter(@NotNull WebSocketSession session, @NotNull TextMessage message, @NotNull Request<?> request) {
        Player player = playerService.getPlayerByRequest(request);
        Room room = codeNamesGame.getGameRoom(playerService.getPlayerRoomID(request));

        return player.getUser().id() == room.getRoomAdminID();
    }
}
