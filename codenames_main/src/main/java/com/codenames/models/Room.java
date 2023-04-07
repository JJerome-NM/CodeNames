package com.codenames.models;

import com.codenames.controllers.WordsController;
import com.codenames.dto.RoomDto;
import com.codenames.dto.UserDto;
import com.codenames.dto.WordDto;
import com.codenames.enums.Color;
import com.codenames.enums.GameTurn;
import com.codenames.enums.PlayerRole;
import com.codenames.enums.GameStatus;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private final int id;

    private GameStatus status = GameStatus.STOPPED;

    private GameTurn gameTurn = null;

    private final User roomAdmin;

    private final Team blueTeam = new Team(Color.BLUE);

    private final Team yellowTeam = new Team(Color.YELLOW);

    private final List<Player> spectators = new ArrayList<>();

    private final List<Word> words = new ArrayList<>();

    private final Settings settings = new Settings();

    private final Timer timer = new Timer();

    private final List<User> bannedUsers = new ArrayList<>();

    Room(int newRoomId, Player roomAdmin){
        this.id = newRoomId;
        this.roomAdmin = roomAdmin.getUser();

        this.spectators.add(roomAdmin);
    }


    public RoomDto getRoomInfo(Player player){

        List<UserDto> spectatorList = this.spectators.stream().map(spec -> spec.getUser().toUserDto()).toList();
        List<WordDto> wordDtoList = this.words.stream().map(word -> word.toWordDto(player.getPlayerRole())).toList();

        return new RoomDto(this.status, this.blueTeam.toTeamDto(), this.yellowTeam.toTeamDto(), spectatorList,
                wordDtoList, this.settings.getWordsSettings().getWordsCount(), this.timer.getTime());
    }

    public void changeGameStatus(Player player, GameStatus newStatus){
        if (player.getUser() != this.roomAdmin){
            return;
        }
        switch (newStatus){
            case RUN -> startGame(player);
            case STOPPED -> stopGame(player);
            case PAUSED -> pauseGame(player);
        }
    }

    private void startGame(Player player){
        this.generateGameWords();

        this.status = GameStatus.RUN;
    }

    private void stopGame(Player player){
        this.changeGameStatus(player, GameStatus.STOPPED);
    }

    private void pauseGame(Player player){
        this.changeGameStatus(player, GameStatus.PAUSED);
    }

    public void generateGameWords(){
        words.clear();
        words.addAll(WordsController.generateRandomWords(this.settings));
    }

    public void selectWord(Player player, int wordID){

    }

    public boolean checkUserBlocked(Player player){
        return this.bannedUsers.contains(player);
    }

    public void banUser(Player player){
        this.bannedUsers.add(player.getUser());
    }

    public void removeUserFromAllRoles(Player player){
        if (this.blueTeam.checkUserInTeam(player)){
            this.blueTeam.removeUserFormTeam(player);

        } else if (this.blueTeam.checkUserInMaster(player)){
            this.blueTeam.removeUserFromMaster(player);

        } else if (this.yellowTeam.checkUserInTeam(player)){
            this.yellowTeam.removeUserFormTeam(player);

        } else if (this.yellowTeam.checkUserInMaster(player)){
            this.yellowTeam.removeUserFromMaster(player);

        } else {
            this.spectators.remove(player);
        }
    }

    public void selectRole(Player player, PlayerRole selectedRole){
        if (this.checkRoleAvailable(selectedRole)){
            this.removeUserFromAllRoles(player);

            switch (selectedRole) {
                case BLUE_PLAYER -> this.blueTeam.addUserToTeam(player);
                case YELLOW_PLAYER -> this.yellowTeam.addUserToTeam(player);
                case BLUE_MASTER -> this.blueTeam.selectMaster(player);
                case YELLOW_MASTER -> this.yellowTeam.selectMaster(player);
            }
            player.setPlayerRole(selectedRole);
        }
    }

    public boolean checkRoleAvailable(PlayerRole playerRole){
        return switch (playerRole) {
            case BLUE_PLAYER -> this.blueTeam.playerRoleAvailable();
            case YELLOW_PLAYER -> this.yellowTeam.playerRoleAvailable();
            case BLUE_MASTER -> this.blueTeam.masterRoleAvailable();
            case YELLOW_MASTER -> this.yellowTeam.masterRoleAvailable();
            default -> false;
        };
    }

    public void addUserToRoom(Player player){
        if (this.checkUserBlocked(player)){
            return;
        }

        player.setPlayerRole(PlayerRole.SPECTATOR);
        this.spectators.add(player);
    }
}
