package com.codenames.controllers;


import com.codenames.dto.RoomDto;
import com.codenames.enums.GameStatus;
import com.codenames.filters.AvailableRoomFilter;
import com.codenames.filters.UserAuthorizedFilter;
import com.codenames.models.AuthorizedUsers;
import com.codenames.models.CodeNamesGame;
import com.codenames.enums.PlayerRole;
import com.codenames.models.Settings;
import com.codenames.models.User;
import com.codenames.models.UserRoomSession;
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

    private final CodeNamesGame codeNamesGame;

    private final MessageSender messageSender;

    private final AuthorizedUsers authorizedUsers;

    private User getUser(Request<?> request){
        return this.authorizedUsers.getUserRoomSession(request.getSessionID()).getPlayer().getUser();
    }

    private UserRoomSession getUserRoomSession(Request<?> request){
        return this.authorizedUsers.getUserRoomSession(request.getSessionID());
    }



    @SocketDisconnectMapping
    public void disconnect(WebSocketSession session, CloseStatus status){
        authorizedUsers.removeUserRoomSession(session.getId());
    }


    @SocketConnectMapping
    public void userConnect(WebSocketSession session) {
        authorizedUsers.addUserRoomSession(session.getId(), -1, new User(100, "100", "random"));

        LOGGER.info(session.getId() + " - connected");
    }


    @SocketMapping(reqPath = "/room/connect")
    @SocketMappingFilters(filters = {
            AvailableRoomFilter.class,
            UserAuthorizedFilter.class
    })
    public void connectToRoom(Request<Integer> request){
        this.codeNamesGame.addUserToRoom(this.getUser(request), request.getRequestBody());
        this.authorizedUsers.setNewRoomID(request.getSessionID(), request.getRequestBody());


        RoomDto roomDto = this.codeNamesGame.getRoomInfo(this.getUserRoomSession(request));

        messageSender.send(request.getSessionID(), "/newRoomInfo", roomDto);
    }


    @SocketMapping(reqPath = "/room/create")
    @SocketMappingFilters(filters = {
            UserAuthorizedFilter.class
    })
    public void createNewRoom(Request<String> request){
        int newRoomID = this.codeNamesGame.createNewRoom(this.getUserRoomSession(request).getPlayer());

        this.getUserRoomSession(request).setRoomID(newRoomID);

        RoomDto roomDto = this.codeNamesGame.getRoomInfo(this.getUserRoomSession(request));

        messageSender.send(request.getSessionID(), "/newRoomInfo", roomDto);
    }


    @SocketMapping(reqPath = "/room/select/role")
    @SocketMappingFilters(filters = {
            UserAuthorizedFilter.class
    })
    public void selectRoomRole(Request<String> request){
        PlayerRole playerRole = PlayerRole.convertToPlayerRole(request.getRequestBody());
        this.codeNamesGame.changeUserRoleInRoom(this.getUserRoomSession(request), playerRole);

        RoomDto roomDto = this.codeNamesGame.getRoomInfo(this.getUserRoomSession(request));

        messageSender.send(request.getSessionID(), "/newRoomInfo", roomDto);
    }


    @SocketMapping(reqPath = "/room/admin/start")
    @SocketMappingFilters(filters = {
            UserAuthorizedFilter.class
    })
    public void startGameRoom(Request<Settings> request){

        this.codeNamesGame.changeGameStatus(this.getUserRoomSession(request), GameStatus.RUN);

        RoomDto roomDto = this.codeNamesGame.getRoomInfo(this.getUserRoomSession(request));

        messageSender.send(request.getSessionID(), "/newRoomInfo", roomDto);
    }


    @SocketMapping(reqPath = "/room/select/word")
    @SocketMappingFilters(filters = {
            UserAuthorizedFilter.class
    })
    public void selectWord(Request<Integer> request){

        this.codeNamesGame.selectWord(this.getUserRoomSession(request), request.getRequestBody());

        RoomDto roomDto = this.codeNamesGame.getRoomInfo(this.getUserRoomSession(request));

        messageSender.send(request.getSessionID(), "/newRoomInfo", roomDto);
    }
}
