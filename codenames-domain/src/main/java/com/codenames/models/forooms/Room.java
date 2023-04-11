package com.codenames.models.forooms;

import com.codenames.models.forgame.Player;
import com.codenames.models.forgame.User;
import com.codenames.enums.Color;
import com.codenames.enums.GameTurn;
import com.codenames.enums.GameStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter
public class Room {

    private final int id;

    private GameStatus status = GameStatus.STOPPED;

    private GameTurn gameTurn = null;

    private final int roomAdminID;

    private final Team blueTeam = new Team(Color.BLUE);

    private final Team yellowTeam = new Team(Color.YELLOW);

    private final List<Player> spectators = new ArrayList<>();

    private final List<Word> words = new ArrayList<>();

    private final Map<Integer, List<Player>> selectedWords = new HashMap<>();

    private final List<Player> selectedSkipTurnPlayers = new ArrayList<>();

    private final Settings settings = new Settings();

    private final Timer timer = new Timer();

    private final List<User> bannedUsers = new ArrayList<>();

    public Room(int newRoomId, Player roomAdmin){
        this.id = newRoomId;
        this.roomAdminID = roomAdmin.getUser().id();

        this.spectators.add(roomAdmin);
    }
}
