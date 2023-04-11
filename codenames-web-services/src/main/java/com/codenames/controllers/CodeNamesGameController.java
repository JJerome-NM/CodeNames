package com.codenames.controllers;


import com.codenames.dto.RoomDto;
import com.codenames.enums.GameStatus;
import com.codenames.enums.PlayerRole;
import com.codenames.filters.method.AvailableRoomFilter;
import com.codenames.filters.method.GameRunningFilter;
import com.codenames.filters.method.SendMessageFilter;
import com.codenames.filters.method.SkipGameTurnFilter;
import com.codenames.filters.method.UserAuthorizedFilter;
import com.codenames.models.forgame.AuthorizedUsers;
import com.codenames.models.forgame.CodeNamesGame;
import com.codenames.models.forgame.Player;
import com.codenames.models.forooms.Room;
import com.codenames.models.forooms.Settings;
import com.codenames.models.forgame.User;
import com.codenames.models.forgame.UserRoomSession;
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

    private static final String NEW_ROOM_INFO_PATH = "/room/new/info"; // add to setting

    private final MessageSender messageSender;

    private final GameService gameService;

    private final RoomService gameRoomService;

    private final CodeNamesGame codeNamesGame;

    private final AuthorizedUsers authorizedUsers;



    private User getUser(Request<?> request){
        return this.authorizedUsers.getUserRoomSession(request.getSessionID()).getPlayer().getUser();
    }

    private UserRoomSession getUserRoomSession(Request<?> request){
        return this.authorizedUsers.getUserRoomSession(request.getSessionID());
    }



    @SocketConnectMapping
    public void userConnect(WebSocketSession session) {
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
        Room room = codeNamesGame.getGameRoom(request.getRequestBody());
        Player player = getUserRoomSession(request).getPlayer();

        gameRoomService.addUserToRoom(room, player);
        this.authorizedUsers.setNewRoomID(request.getSessionID(), request.getRequestBody());

        RoomDto roomDto = gameRoomService.getRoomInfo(room, player);

        messageSender.send(request.getSessionID(), NEW_ROOM_INFO_PATH, roomDto);
    }


    @SocketMapping(reqPath = "/room/create")
    @SocketMappingFilters(filters = {
            UserAuthorizedFilter.class
    })
    public void createNewRoom(Request<String> request){
        int newRoomID = gameService.createNewRoom(codeNamesGame, getUserRoomSession(request).getPlayer());
        this.getUserRoomSession(request).setRoomID(newRoomID);

        Room room = codeNamesGame.getGameRoom(newRoomID);
        Player player = getUserRoomSession(request).getPlayer();

        RoomDto roomDto = gameRoomService.getRoomInfo(room, player);

        messageSender.send(request.getSessionID(), NEW_ROOM_INFO_PATH, roomDto);
    }


    @SocketMapping(reqPath = "/room/select/role")
    @SocketMappingFilters(filters = {
            UserAuthorizedFilter.class,
    })
    public void selectRoomRole(Request<String> request){
        Room room = codeNamesGame.getGameRoom(getUserRoomSession(request).getRoomID());
        Player player = getUserRoomSession(request).getPlayer();
        PlayerRole playerRole = PlayerRole.convertToPlayerRole(request.getRequestBody());

        gameRoomService.selectRole(room, player, playerRole);

        RoomDto roomDto = gameRoomService.getRoomInfo(room, player);
        messageSender.send(request.getSessionID(), NEW_ROOM_INFO_PATH, roomDto);
    }


    @SocketMapping(reqPath = "/room/select/word")
    @SocketMappingFilters(filters = {
            UserAuthorizedFilter.class,
            GameRunningFilter.class
    })
    public void selectWord(Request<Integer> request){
        Room room = codeNamesGame.getGameRoom(getUserRoomSession(request).getRoomID());
        Player player = getUserRoomSession(request).getPlayer();

        gameRoomService.selectWord(room, player, request.getRequestBody());

        RoomDto roomDto = gameRoomService.getRoomInfo(room, player);
        messageSender.send(request.getSessionID(), NEW_ROOM_INFO_PATH, roomDto);
    }


    @SocketMapping(reqPath = "/room/endTurn")
    @SocketMappingFilters(filters = {
            UserAuthorizedFilter.class,
            GameRunningFilter.class,
            SkipGameTurnFilter.class
    })
    public void endTurn(Request<String> request){
        Room room = codeNamesGame.getGameRoom(getUserRoomSession(request).getRoomID());
        Player player = getUserRoomSession(request).getPlayer();

        gameRoomService.skipTurn(room, player);

        RoomDto roomDto = gameRoomService.getRoomInfo(room, player);
        messageSender.send(request.getSessionID(), NEW_ROOM_INFO_PATH, roomDto);
    }

    @SocketMapping(reqPath = "/room/sendMassage")
    @SocketMappingFilters(filters = {
            UserAuthorizedFilter.class,
            GameRunningFilter.class,
            SendMessageFilter.class
    })
    public void sendMasterMassage(Request<String> request){
        Room room = codeNamesGame.getGameRoom(getUserRoomSession(request).getRoomID());
        Player player = getUserRoomSession(request).getPlayer();

        gameRoomService.sendMessage(room, player, request.getRequestBody());

        RoomDto roomDto = gameRoomService.getRoomInfo(room, player);
        messageSender.send(request.getSessionID(), NEW_ROOM_INFO_PATH, roomDto);
    }


    @SocketMapping(reqPath = "/room/admin/start")
    @SocketMappingFilters(filters = {
            UserAuthorizedFilter.class
    })
    public void startGameRoom(Request<Settings> request){
        Room room = codeNamesGame.getGameRoom(getUserRoomSession(request).getRoomID());
        Player player = getUserRoomSession(request).getPlayer();

        gameRoomService.changeGameStatus(room, player, GameStatus.RUN);

        RoomDto roomDto = gameRoomService.getRoomInfo(room, player);
        messageSender.send(request.getSessionID(), NEW_ROOM_INFO_PATH, roomDto);
    }

}
