package com.codenames.services;

import com.codenames.dto.RoomDto;
import com.codenames.enums.GameStatus;
import com.codenames.enums.GameTurn;
import com.codenames.enums.PlayerRole;
import com.codenames.mapper.RoomMapper;
import com.codenames.models.game.Player;
import com.codenames.models.room.Room;
import com.codenames.models.room.Team;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RoomService {

    private final TeamService teamService;

    private final WordsService wordsService;

    private final RoomMapper roomMapper;

    public RoomDto getRoomInfo(Room room, Player player){
        return roomMapper.roomToRoomDto(room, player);
    }

    public void changeGameStatus(Room room, Player player, GameStatus newStatus){
        if (player.getUser().id() != room.getRoomAdminID()){
            return;
        }
        switch (newStatus){
            case RUN -> startGame(room);
            case STOPPED -> stopGame(room);
            case PAUSED -> pauseGame(room);
        }
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

        skipTurn(room, player);
    }

    public void skipTurn(Room room, Player player){
        room.setGameTurn(room.getGameTurn().nextTurn());
    }

    public void selectWord(Room room, Player player, int wordID){
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

        if (teamService.checkUserInTeam(blueTeam, player)){
            teamService.removeUserFormTeam(blueTeam, player);

        } else if (teamService.checkUserInMaster(blueTeam, player)){
            teamService.removeUserFromMaster(blueTeam, player);

        } else if (teamService.checkUserInTeam(yellowTeam, player)){
            teamService.removeUserFormTeam(yellowTeam, player);

        } else if (teamService.checkUserInMaster(yellowTeam, player)){
            teamService.removeUserFromMaster(yellowTeam, player);

        } else {
            room.getSpectators().remove(player);
        }
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
