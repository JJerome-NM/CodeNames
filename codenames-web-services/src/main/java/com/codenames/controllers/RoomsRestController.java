package com.codenames.controllers;


import com.codenames.domain.game.CodeNamesGame;
import com.codenames.domain.game.Player;
import com.codenames.domain.game.User;
import com.codenames.exception.UserNotFoundException;
import com.codenames.mapper.UserMapper;
import com.codenames.services.GameService;
import com.codenames.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomsRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomsRestController.class);

    private final GameService gameService;

    private final CodeNamesGame codeNamesGame;

    private final UserMapper userMapper;

    private final UserService userService;

    @GetMapping(path = "/connect/{id}")
    public int connectToRoomById(@PathVariable("id") int roomID) {
        return gameService.checkAvailabilityRoom(codeNamesGame, roomID) ? roomID : -1;
    }

    @GetMapping(path = "/create")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Integer createNewRoom(Authentication authentication) {
        User user = userMapper.userEntityToUser(userService.getUserEntityFromAuthentication(authentication)
                .orElseThrow(() ->
                        new UserNotFoundException("Error receiving an authorized user", HttpStatus.UNAUTHORIZED)));

        int newRoomID = gameService.createNewRoom(codeNamesGame, new Player(null, null, user));

        LOGGER.info("Created new room, id - " + newRoomID);

        return newRoomID;
    }

}
