package com.codenames.services;

import com.codenames.dto.RoomDto;
import com.codenames.dto.UserDto;
import com.codenames.dto.WordDto;
import com.codenames.enums.GameStatus;
import com.codenames.enums.GameTurn;
import com.codenames.enums.PlayerRole;
import com.codenames.models.for_game.Player;
import com.codenames.models.for_rooms.Room;
import com.codenames.models.for_rooms.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GameRoomService {

    private final TeamService teamService;

    public RoomDto getRoomInfo(Room room, Player player){
        List<UserDto> spectatorList = room.getSpectators().stream().map(spec -> spec.getUser().toUserDto()).toList();
        List<WordDto> wordDtoList = room.getWords().stream().map(word -> word.toWordDto(player.getPlayerRole())).toList();

        return new RoomDto(room.getStatus(), room.getBlueTeam().toTeamDto(), room.getYellowTeam().toTeamDto(),
                spectatorList, wordDtoList, room.getSettings().getWordsSettings().getWordsCount(), room.getTimer().getTime());
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
        room.setGameTurn(GameTurn.BLUE_PLAYERS);
    }

    private void stopGame(Room room){

    }

    private void pauseGame(Room room){

    }

    private void generateGameWords(Room room){
        room.getWords().clear();
        room.getWords().addAll(WordsService.generateRandomWords(room.getSettings()));
    }

    public void selectWord(final Room room, Player player, int wordID){
        final Map<Integer, List<Player>> selectedWords = room.getSelectedWords();
        final GameTurn gameTurn = room.getGameTurn();

        if (gameTurn.checkUserTurn(player.getPlayerRole())){
            if (selectedWords.containsKey(wordID)){
                selectedWords.get(wordID).add(player);
            } else {
                selectedWords.put(wordID, List.of(player));
            }

            if (gameTurn == GameTurn.BLUE_PLAYERS
                    && teamService.compareTeamPlayers(room.getBlueTeam(), selectedWords.get(wordID))
                    || gameTurn == GameTurn.YELLOW_PLAYERS
                    && teamService.compareTeamPlayers(room.getYellowTeam(), selectedWords.get(wordID))) {
                selectWord(room, wordID);
            }
        }
    }
    private void selectWord(Room room, int wordID){
        room.getWords().forEach(word -> {
            if (word.getId() == wordID){
                word.selectWord();
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
