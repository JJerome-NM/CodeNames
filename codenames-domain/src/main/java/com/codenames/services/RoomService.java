package com.codenames.services;

import com.codenames.dto.RoomDto;
import com.codenames.enums.GameStatus;
import com.codenames.enums.GameTurn;
import com.codenames.enums.PlayerRole;
import com.codenames.mapper.RoomMapper;
import com.codenames.domain.game.Player;
import com.codenames.domain.room.Room;
import com.codenames.domain.room.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RoomService {

    private final TeamService teamService;

    private final WordsService wordsService;

    private final RoomMapper roomMapper;

    public RoomDto getRoomInfo(Room room, boolean wordsColorHidden){
        return roomMapper.roomToRoomDto(room, wordsColorHidden);
    }

    public void changeGameStatus(Room room, GameStatus newStatus){
        switch (newStatus){
            case RUN -> startGame(room);
            case STOPPED -> stopGame(room);
            case PAUSED -> pauseGame(room);
        }
    }

    public void restartGame(Room room){
        stopGame(room);
        startGame(room);
    }

    private void startGame(Room room){
        generateGameWords(room);

        room.setStatus(GameStatus.RUN);
        room.setGameTurn(GameTurn.BLUE_MASTER);
    }

    private void stopGame(Room room){
        room.setStatus(GameStatus.STOPPED);
        room.setGameTurn(null);
    }

    private void pauseGame(Room room){
        room.setStatus(GameStatus.PAUSED);
    }

    public boolean checkGameStatus(Room room, GameStatus gameStatus){
        return room.getStatus() == gameStatus;
    }

    private void generateGameWords(Room room){
        room.getWords().clear();
        room.getWords().addAll(wordsService.generateRandomWords(room.getSettings()));
    }

    public void sendMessage(Room room, Player player, String message){
        if (player.getPlayerRole() == PlayerRole.BLUE_MASTER){
            teamService.addMessage(room.getBlueTeam(), message);
        } else {
            teamService.addMessage(room.getYellowTeam(), message);
        }

        skipTurn(room);
    }

    public void skipTurn(Room room){
        room.setGameTurn(room.getGameTurn().nextTurn());
    }

    public void selectWord(Room room, int wordID){
        room.getWords().forEach(word -> {
            if (word.getId() == wordID){
                word.setHidden(false);
            }
        });
        room.getSelectedWords().clear();
    }

    public boolean checkUserBlocked(Room room, Player player){
        return room.getBannedUsers().contains(player);
    }

    public void banUser(Room room, Player player){
        room.getBannedUsers().add(player.getUser());
    }

    public void removeUserFromAllRoles(Room room, Player player){
        final Team blueTeam = room.getBlueTeam();
        final Team yellowTeam = room.getYellowTeam();

        teamService.removeUserFromTeam(blueTeam, player);
        teamService.removeUserFromMaster(blueTeam, player);
        teamService.removeUserFromTeam(yellowTeam, player);
        teamService.removeUserFromMaster(yellowTeam, player);
        room.getSpectators().removeIf(player1 -> player1.equals(player));
    }

    public void selectRole(Room room, Player player, PlayerRole selectedRole){
        removeUserFromAllRoles(room, player);

        switch (selectedRole) {
            case BLUE_PLAYER -> teamService.addUserToTeam(room.getBlueTeam(), player);
            case YELLOW_PLAYER -> teamService.addUserToTeam(room.getYellowTeam(), player);
            case BLUE_MASTER -> teamService.selectMaster(room.getBlueTeam(), player);
            case YELLOW_MASTER -> teamService.selectMaster(room.getYellowTeam(), player);
            default -> {
                room.getSpectators().add(player);
                player.setPlayerRole(PlayerRole.SPECTATOR);
                return;
            }
        }
        player.setPlayerRole(selectedRole);
    }

    public boolean checkRoleAvailable(Room room, PlayerRole playerRole){
        return switch (playerRole) {
            case BLUE_PLAYER -> teamService.playerRoleAvailable(room.getBlueTeam());
            case YELLOW_PLAYER -> teamService.playerRoleAvailable(room.getYellowTeam());
            case BLUE_MASTER -> teamService.masterRoleAvailable(room.getBlueTeam());
            case YELLOW_MASTER -> teamService.masterRoleAvailable(room.getYellowTeam());
            default -> false;
        };
    }

    public void addUserToRoom(Room room, Player player){
        player.setPlayerRole(PlayerRole.SPECTATOR);
        room.getSpectators().add(player);
    }
}
