package com.codenames.controllers;


import com.codenames.enums.PlayerRole;
import com.codenames.filters.method.AvailableRoomFilter;
import com.codenames.filters.method.GameRunningFilter;
import com.codenames.filters.method.GameStoppedFilter;
import com.codenames.filters.method.SelectWordAvailableFilter;
import com.codenames.filters.method.SendMessageFilter;
import com.codenames.filters.method.SkipGameTurnFilter;
import com.codenames.filters.method.UserAuthorizedFilter;
import com.codenames.models.game.AuthorizedUsers;
import com.codenames.models.game.CodeNamesGame;
import com.codenames.models.game.Player;
import com.codenames.models.room.Room;
import com.codenames.models.game.User;
import com.codenames.services.PlayerService;
import com.codenames.services.RoomService;
import com.codenames.services.GameService;
import com.jjerome.annotations.SocketConnectMapping;
import com.jjerome.annotations.SocketController;
import com.jjerome.annotations.SocketDisconnectMapping;
import com.jjerome.annotations.SocketMapping;
import com.jjerome.annotations.SocketMappingFilters;
import com.jjerome.dto.Request;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;

@SocketController
@RequiredArgsConstructor
public class GameRoomsWebSocketController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameRoomsWebSocketController.class);

    private final GameService gameService;

    private final RoomService roomService;

    private final PlayerService playerService;

    private final CodeNamesGame codeNamesGame;

    private final AuthorizedUsers authorizedUsers;


    @SocketConnectMapping
    public void userConnect(WebSocketSession session) {
        // TODO: 18.04.2023 authorizedUsers - used temporarily, after adding the database will be recycled
        authorizedUsers.addUserRoomSession(session.getId(), -1, new User(100, "100", "random"));

        LOGGER.info(session.getId() + " - connected");
    }

    @SocketDisconnectMapping
    public void disconnect(WebSocketSession session, CloseStatus status){
        authorizedUsers.removeUserRoomSession(playerService.getPlayerBySessionID(session.getId()));
    }



    @SocketMapping(reqPath = "/room/connect")
    @SocketMappingFilters(filters = {
            UserAuthorizedFilter.class,
            AvailableRoomFilter.class
    })
    public void connectToRoom(Request<Integer> request){
        Room room = gameService.getRoomByID(codeNamesGame, request.getRequestBody());
        Player player = playerService.getPlayerByRequest(request);

        roomService.addUserToRoom(room, player);
        this.authorizedUsers.setNewRoomID(request.getSessionID(), request.getRequestBody());

        gameService.sendRoomInfoToAllRoomPlayer(room);
    }


    @SocketMapping(reqPath = "/room/select/role")
    @SocketMappingFilters(filters = {
            UserAuthorizedFilter.class,
            GameStoppedFilter.class
    })
    public void selectRoomRole(Request<String> request){
        Room room = codeNamesGame.getGameRoom(playerService.getPlayerRoomID(request));
        Player player = playerService.getPlayerByRequest(request);
        PlayerRole playerRole = PlayerRole.convertToPlayerRole(request.getRequestBody());

        roomService.selectRole(room, player, playerRole);
        gameService.sendRoomInfoToAllRoomPlayer(room);
    }


    @SocketMapping(reqPath = "/room/select/word")
    @SocketMappingFilters(filters = {
            UserAuthorizedFilter.class,
            GameRunningFilter.class,
            SelectWordAvailableFilter.class
    })
    public void selectWord(Request<Integer> request){
        Room room = codeNamesGame.getGameRoom(playerService.getPlayerRoomID(request));

        roomService.selectWord(room, request.getRequestBody());

        gameService.sendRoomInfoToAllRoomPlayer(room);
    }


    @SocketMapping(reqPath = "/room/endTurn")
    @SocketMappingFilters(filters = {
            UserAuthorizedFilter.class,
            GameRunningFilter.class,
            SkipGameTurnFilter.class
    })
    public void endTurn(Request<String> request){
        Room room = codeNamesGame.getGameRoom(playerService.getPlayerRoomID(request));

        roomService.skipTurn(room);
        gameService.sendRoomInfoToAllRoomPlayer(room);
    }

    @SocketMapping(reqPath = "/room/sendMassage")
    @SocketMappingFilters(filters = {
            UserAuthorizedFilter.class,
            GameRunningFilter.class,
            SendMessageFilter.class
    })
    public void sendMasterMassage(Request<String> request){
        Room room = codeNamesGame.getGameRoom(playerService.getPlayerRoomID(request));
        Player player = playerService.getPlayerByRequest(request);

        roomService.sendMessage(room, player, request.getRequestBody());

        gameService.sendRoomInfoToAllRoomPlayer(room);
    }
}
