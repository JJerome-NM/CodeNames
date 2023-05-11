package com.codenames.controllers;


import com.codenames.models.game.CodeNamesGame;
import com.codenames.models.game.Player;
import com.codenames.models.game.User;
import com.codenames.services.GameService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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

    @GetMapping(path = "/connect/{id}")
    public int connectToRoomById(@PathVariable("id") int roomID){
        return gameService.checkAvailabilityRoom(codeNamesGame, roomID) ? roomID : -1;
    }

    @GetMapping(path = "/create")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Integer createNewRoom(){
        // TODO: 03.05.2023 This is a temporary code, it will be recycled after adding authorization 
        int newRoomID = gameService.createNewRoom(codeNamesGame,
                new Player(null ,null, new User(23243, "234", "JJerome")));

        LOGGER.info("Created new room, id - " + newRoomID);

        return newRoomID;
    }

}
