package com.codenames.controllers;


import com.codenames.dto.RoomDto;
import com.codenames.enums.GameStatus;
import com.codenames.enums.PlayerRole;
import com.codenames.filters.method.AvailableRoomFilter;
import com.codenames.filters.method.GameRunningFilter;
import com.codenames.filters.method.SelectWordAvailableFilter;
import com.codenames.filters.method.SendMessageFilter;
import com.codenames.filters.method.SkipGameTurnFilter;
import com.codenames.filters.method.UserAuthorizedFilter;
import com.codenames.models.game.AuthorizedUsers;
import com.codenames.models.game.CodeNamesGame;
import com.codenames.models.game.Player;
import com.codenames.models.room.Room;
import com.codenames.models.room.Settings;
import com.codenames.models.game.User;
import com.codenames.properties.DefaultMessagePathProperties;
import com.codenames.services.PlayerService;
import com.codenames.services.RoomService;
import com.codenames.services.GameService;
import com.jjerome.annotations.SocketConnectMapping;
import com.jjerome.annotations.SocketController;
import com.jjerome.annotations.SocketDisconnectMapping;
import com.jjerome.annotations.SocketMapping;
import com.jjerome.annotations.SocketMappingFilters;
import com.jjerome.dto.Request;
import com.jjerome.models.MessageSender;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;

@SocketController
@RequiredArgsConstructor
public class CodeNamesGameController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeNamesGameController.class);

    private final MessageSender messageSender;

    private final GameService gameService;

    private final RoomService roomService;

    private final PlayerService playerService;

    private final CodeNamesGame codeNamesGame;

    private final AuthorizedUsers authorizedUsers;

    private final DefaultMessagePathProperties defaultMessagePath;


    @SocketConnectMapping
    public void userConnect(WebSocketSession session) {
        // TODO: 18.04.2023 authorizedUsers - used temporarily, after adding the database will be recycled
        authorizedUsers.addUserRoomSession(session.getId(), -1, new User(100, "100", "random"));

        LOGGER.info(session.getId() + " - connected");
    }

    @SocketDisconnectMapping
    public void disconnect(WebSocketSession session, CloseStatus status){
        authorizedUsers.removeUserRoomSession(session.getId());
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

        gameService.sendNewRoomInfoToPlayer(request, room, player);
    }


    @SocketMapping(reqPath = "/room/create")
    @SocketMappingFilters(filters = {
            UserAuthorizedFilter.class
    })
    public void createNewRoom(Request<String> request){
        // TODO: 18.04.2023  Add a check to see if the user has a room


        int newRoomID = gameService.createNewRoom(codeNamesGame, playerService.getPlayerByRequest(request));
        playerService.setPlayerRoomID(request, newRoomID);

        Room room = gameService.getRoomByID(codeNamesGame, newRoomID);
        Player player = playerService.getPlayerByRequest(request);

        LOGGER.info("Created new room, id - " + newRoomID);

        gameService.sendNewRoomInfoToPlayer(request, room, player);
    }


    @SocketMapping(reqPath = "/room/select/role")
    @SocketMappingFilters(filters = {
            UserAuthorizedFilter.class,
    })
    public void selectRoomRole(Request<String> request){
        Room room = codeNamesGame.getGameRoom(playerService.getPlayerRoomID(request));
        Player player = playerService.getPlayerByRequest(request);
        PlayerRole playerRole = PlayerRole.convertToPlayerRole(request.getRequestBody());

        roomService.selectRole(room, player, playerRole);
        gameService.sendNewRoomInfoToPlayer(request, room, player);
    }


    @SocketMapping(reqPath = "/room/select/word")
    @SocketMappingFilters(filters = {
            UserAuthorizedFilter.class,
            GameRunningFilter.class,
            SelectWordAvailableFilter.class
    })
    public void selectWord(Request<Integer> request){
        Room room = codeNamesGame.getGameRoom(playerService.getPlayerRoomID(request));
        Player player = playerService.getPlayerByRequest(request);

        roomService.selectWord(room, player, request.getRequestBody());
        gameService.sendNewRoomInfoToPlayer(request, room, player);
    }


    @SocketMapping(reqPath = "/room/endTurn")
    @SocketMappingFilters(filters = {
            UserAuthorizedFilter.class,
            GameRunningFilter.class,
            SkipGameTurnFilter.class
    })
    public void endTurn(Request<String> request){
        Room room = codeNamesGame.getGameRoom(playerService.getPlayerRoomID(request));
        Player player = playerService.getPlayerByRequest(request);

        roomService.skipTurn(room, player);
        gameService.sendNewRoomInfoToPlayer(request, room, player);
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
        gameService.sendNewRoomInfoToPlayer(request, room, player);
    }


    @SocketMapping(reqPath = "/room/admin/start")
    @SocketMappingFilters(filters = {
            UserAuthorizedFilter.class
    })
    public void startGameRoom(Request<Settings> request){
        Room room = codeNamesGame.getGameRoom(playerService.getPlayerRoomID(request));
        Player player = playerService.getPlayerByRequest(request);

        roomService.changeGameStatus(room, player, GameStatus.RUN);

        RoomDto roomDto = roomService.getRoomInfo(room, player);
        messageSender.send(request.getSessionID(), defaultMessagePath.getNewRoomInfoPath(), roomDto);
    }

}
