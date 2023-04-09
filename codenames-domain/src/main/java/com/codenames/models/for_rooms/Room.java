package com.codenames.models.for_rooms;

import com.codenames.models.for_game.Player;
import com.codenames.models.for_game.User;
import com.codenames.services.WordsService;
import com.codenames.enums.Color;
import com.codenames.enums.GameTurn;
import com.codenames.enums.PlayerRole;
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

    private final Settings settings = new Settings();

    private final Timer timer = new Timer();

    private final List<User> bannedUsers = new ArrayList<>();

    public Room(int newRoomId, Player roomAdmin){
        this.id = newRoomId;
        this.roomAdminID = roomAdmin.getUser().id();

        this.spectators.add(roomAdmin);
    }

//    public void changeGameStatus(Player player, GameStatus newStatus){
//        if (player.getUser() != this.roomAdmin){
//            return;
//        }
//        switch (newStatus){
//            case RUN -> startGame(player);
//            case STOPPED -> stopGame(player);
//            case PAUSED -> pauseGame(player);
//        }
//    }
//
//    private void startGame(Player player){
//        this.generateGameWords();
//
//        this.status = GameStatus.RUN;
//        this.gameTurn = GameTurn.BLUE_PLAYERS;
//    }
//
//    private void stopGame(Player player){
//        this.changeGameStatus(player, GameStatus.STOPPED);
//    }
//
//    private void pauseGame(Player player){
//        this.changeGameStatus(player, GameStatus.PAUSED);
//    }

//    public void generateGameWords(){
//        words.clear();
//        words.addAll(WordsService.generateRandomWords(this.settings));
//    }

//    public void selectWord(Player player, int wordID){
//        if (this.gameTurn.checkUserTurn(player.getPlayerRole())){
//
//            if (this.selectedWords.containsKey(wordID)){
//                this.selectedWords.get(wordID).add(player);
//            } else {
//                this.selectedWords.put(wordID, List.of(player));
//            }
//
//            if (this.gameTurn == GameTurn.BLUE_PLAYERS){
//                if (this.blueTeam.compareTeamPlayers(this.selectedWords.get(wordID))){
//                    this.selectWord(wordID);
//                }
//            } else if (this.gameTurn == GameTurn.YELLOW_PLAYERS){
//                if (this.yellowTeam.compareTeamPlayers(this.selectedWords.get(wordID))){
//                    this.selectWord(wordID);
//                }
//            }
//        }
//    }
//
//    private void selectWord(int wordID){
//        this.words.forEach(word -> {
//            if (word.getId() == wordID){
//                word.selectWord();
//            }
//        });
//        this.selectedWords.clear();
//    }

//    public boolean checkUserBlocked(Player player){
//        return this.bannedUsers.contains(player);
//    }
//
//    public void banUser(Player player){
//        this.bannedUsers.add(player.getUser());
//    }
//
//    public void removeUserFromAllRoles(Player player){
//        if (this.blueTeam.checkUserInTeam(player)){
//            this.blueTeam.removeUserFormTeam(player);
//
//        } else if (this.blueTeam.checkUserInMaster(player)){
//            this.blueTeam.removeUserFromMaster(player);
//
//        } else if (this.yellowTeam.checkUserInTeam(player)){
//            this.yellowTeam.removeUserFormTeam(player);
//
//        } else if (this.yellowTeam.checkUserInMaster(player)){
//            this.yellowTeam.removeUserFromMaster(player);
//
//        } else {
//            this.spectators.remove(player);
//        }
//    }
//
//    public void selectRole(Player player, PlayerRole selectedRole){
//        if (this.checkRoleAvailable(selectedRole)){
//            this.removeUserFromAllRoles(player);
//
//            switch (selectedRole) {
//                case BLUE_PLAYER -> this.blueTeam.addUserToTeam(player);
//                case YELLOW_PLAYER -> this.yellowTeam.addUserToTeam(player);
//                case BLUE_MASTER -> this.blueTeam.selectMaster(player);
//                case YELLOW_MASTER -> this.yellowTeam.selectMaster(player);
//            }
//            player.setPlayerRole(selectedRole);
//        }
//    }
//
//    public boolean checkRoleAvailable(PlayerRole playerRole){
//        return switch (playerRole) {
//            case BLUE_PLAYER -> this.blueTeam.playerRoleAvailable();
//            case YELLOW_PLAYER -> this.yellowTeam.playerRoleAvailable();
//            case BLUE_MASTER -> this.blueTeam.masterRoleAvailable();
//            case YELLOW_MASTER -> this.yellowTeam.masterRoleAvailable();
//            default -> false;
//        };
//    }
//
//    public void addUserToRoom(Player player){
//        if (this.checkUserBlocked(player)){
//            return;
//        }
//
//        player.setPlayerRole(PlayerRole.SPECTATOR);
//        this.spectators.add(player);
//    }
}
