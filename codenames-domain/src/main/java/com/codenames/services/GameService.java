package com.codenames.services;

import com.codenames.exceptions.RoomLimitIsOver;
import com.codenames.models.game.CodeNamesGame;
import com.codenames.models.game.User;
import com.codenames.models.room.Room;
import com.codenames.models.game.Player;
import com.codenames.properties.CodeNamesProperties;
import com.codenames.properties.WSResponsePathProperties;
import com.jjerome.dto.Request;
import com.jjerome.models.MessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GameService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameService.class);

    private final Random random = new Random();

    private final MessageSender messageSender;

    private final PlayerService playerService;

    private final RoomService roomService;

    private final WordsService wordsService;

    private final CodeNamesProperties codeNamesProperties;

    private final WSResponsePathProperties wsResponsePathProperties;

    private final int minRoomID;

    private final int maxRoomID;

    private final int roomLimit;

    public GameService(MessageSender messageSender,
                       PlayerService playerService,
                       RoomService roomService,
                       WordsService wordsService,
                       CodeNamesProperties codeNamesProperties) {
        this.messageSender = messageSender;
        this.playerService = playerService;
        this.roomService = roomService;
        this.wordsService = wordsService;
        this.codeNamesProperties = codeNamesProperties;
        this.wsResponsePathProperties = codeNamesProperties.getWsResponsePaths();
        this.minRoomID = codeNamesProperties.getGameRooms().getMinRoomId();
        this.maxRoomID = codeNamesProperties.getGameRooms().getMaxRoomId();
        this.roomLimit = maxRoomID - minRoomID;
    }

    public boolean checkAvailabilityRoom(CodeNamesGame codeNamesGame, int roomID){
        return codeNamesGame.getGameRooms().containsKey(roomID);
    }

    public Room getRoomByRequest(CodeNamesGame codeNamesGame, Request<?> request){
        return codeNamesGame.getGameRoom(playerService.getPlayerRoomID(request));
    }

    public Room getRoomByID(CodeNamesGame codeNamesGame, int roomID){
        return codeNamesGame.getGameRoom(roomID);
    }

    public void sendRoomInfoToAllRoomPlayer(Room room){
        sendRoomInfoToPlayer(room.getBlueTeam().getMaster(), room, false);
        sendRoomInfoToPlayer(room.getYellowTeam().getMaster(), room, false);

        room.getSpectators().forEach(player -> sendRoomInfoToPlayer(player, room, true));
        room.getBlueTeam().getPlayersList().forEach(player -> sendRoomInfoToPlayer(player, room, true));
        room.getYellowTeam().getPlayersList().forEach(player -> sendRoomInfoToPlayer(player, room, true));
    }

    private void sendRoomInfoToPlayer(Player player, Room room, boolean hidden){
        if (player != null && player.getWsSessionId() != null){
            messageSender.send(player.getWsSessionId(),
                    wsResponsePathProperties.getNewRoomInfoPath(), roomService.getRoomInfo(room, hidden));
        }
    }

    public void addUserToBanList(CodeNamesGame codeNamesGame, User user){
        codeNamesGame.getBannedUsers().add(user);
    }

    public boolean checkUserBlocked(CodeNamesGame codeNamesGame, User user){
        return codeNamesGame.getBannedUsers().contains(user);
    }

    public void removeUserFromBanList(CodeNamesGame codeNamesGame, User user){
        codeNamesGame.getBannedUsers().remove(user);
    }

    public int createNewRoom(CodeNamesGame codeNamesGame, Player roomAdmin){
        if (codeNamesGame.getGameRooms().size() > roomLimit){
            throw new RoomLimitIsOver("Room limit is over!");
        }
        int newRoomID = generateRoomID(codeNamesGame);

        codeNamesGame.getGameRooms().put(newRoomID, new Room(newRoomID, roomAdmin));

        return newRoomID;
    }

    private int generateRoomID(CodeNamesGame codeNamesGame){
        int newRoomId = random.nextInt(maxRoomID - minRoomID) + minRoomID;
        while (codeNamesGame.getGameRooms().containsKey(newRoomId)){
            newRoomId++;
        }
        return newRoomId;
    }
}
