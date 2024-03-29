package com.codenames.controllers;

import com.codenames.enums.GameStatus;
import com.codenames.filters.method.GameStoppedFilter;
import com.codenames.domain.game.CodeNamesGame;
import com.codenames.domain.room.Room;
import com.codenames.domain.room.Settings;
import com.codenames.filters.method.UserIsRoomAdminFilter;
import com.codenames.services.GameService;
import com.codenames.services.PlayerService;
import com.codenames.services.RoomService;
import com.jjerome.annotations.SocketController;
import com.jjerome.annotations.SocketMapping;
import com.jjerome.annotations.SocketMappingFilters;
import com.jjerome.dto.Request;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SocketController
@RequiredArgsConstructor
public class AdminWebSocketController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminWebSocketController.class);

    private final GameService gameService;

    private final RoomService roomService;

    private final PlayerService playerService;

    private final CodeNamesGame codeNamesGame;

    @SocketMapping(reqPath = "/room/admin/start")
    @SocketMappingFilters(filters = {
            GameStoppedFilter.class,
            UserIsRoomAdminFilter.class
    })
    public void startGameInRoom(Request<Settings> request){
        Room room = codeNamesGame.getGameRoom(playerService.getPlayerRoomID(request));

        roomService.changeGameStatus(room, GameStatus.RUN);

        gameService.sendRoomInfoToAllRoomPlayer(room);
    }

    @SocketMapping(reqPath = "/room/admin/stop")
    @SocketMappingFilters(filters = {
            UserIsRoomAdminFilter.class
    })
    public void stopGameInRoom(Request<String> request){
        Room room = codeNamesGame.getGameRoom(playerService.getPlayerRoomID(request));

        roomService.changeGameStatus(room, GameStatus.STOPPED);

        gameService.sendRoomInfoToAllRoomPlayer(room);
    }

    @SocketMapping(reqPath = "/room/admin/restart")
    @SocketMappingFilters(filters = {
            UserIsRoomAdminFilter.class
    })
    public void restartGameInRoom(Request<String> request){
        Room room = codeNamesGame.getGameRoom(playerService.getPlayerRoomID(request));

        roomService.restartGame(room);

        gameService.sendRoomInfoToAllRoomPlayer(room);
    }

}
