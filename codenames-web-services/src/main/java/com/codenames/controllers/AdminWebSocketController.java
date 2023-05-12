package com.codenames.controllers;

import com.codenames.enums.GameStatus;
import com.codenames.filters.method.GameStoppedFilter;
import com.codenames.filters.method.UserAuthorizedFilter;
import com.codenames.models.game.CodeNamesGame;
import com.codenames.models.room.Room;
import com.codenames.models.room.Settings;
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
            UserAuthorizedFilter.class,
            GameStoppedFilter.class,
            // TODO: 05.05.2023 Temporarily to simplify tests 
//            UserIsRoomAdminFilter.class
    })
    public void startGameInRoom(Request<Settings> request){
        Room room = codeNamesGame.getGameRoom(playerService.getPlayerRoomID(request));

        roomService.changeGameStatus(room, GameStatus.RUN);

        gameService.sendRoomInfoToAllRoomPlayer(room);
    }

    @SocketMapping(reqPath = "/room/admin/stop")
    @SocketMappingFilters(filters = {
            UserAuthorizedFilter.class
            // TODO: 05.05.2023 Temporarily to simplify tests
//            UserIsRoomAdminFilter.class
    })
    public void stopGameInRoom(Request<String> request){
        Room room = codeNamesGame.getGameRoom(playerService.getPlayerRoomID(request));

        roomService.changeGameStatus(room, GameStatus.STOPPED);

        gameService.sendRoomInfoToAllRoomPlayer(room);
    }

    @SocketMapping(reqPath = "/room/admin/restart")
    @SocketMappingFilters(filters = {
            UserAuthorizedFilter.class
            // TODO: 05.05.2023 Temporarily to simplify tests
//            UserIsRoomAdminFilter.class
    })
    public void restartGameInRoom(Request<String> request){
        Room room = codeNamesGame.getGameRoom(playerService.getPlayerRoomID(request));

        roomService.restartGame(room);

        gameService.sendRoomInfoToAllRoomPlayer(room);
    }

}
