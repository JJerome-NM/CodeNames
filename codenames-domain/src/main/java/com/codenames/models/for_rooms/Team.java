package com.codenames.models.for_rooms;

import com.codenames.dto.TeamDto;
import com.codenames.dto.UserDto;
import com.codenames.enums.Color;
import com.codenames.models.for_game.Player;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Getter @Setter
public class Team {

    private final Color color;

    private int score;

    private Player master;

    private final Map<Integer, Player> players = new HashMap<>();

    private final List<String> messages = new ArrayList<>();

    @Deprecated
    public TeamDto toTeamDto(){
        List<UserDto> playersList = this.players.values().stream().map(Player::toUserDto).toList();
        UserDto masterDto = this.master != null ? this.master.toUserDto() : null;

        return new TeamDto(this.color, this.score, masterDto, playersList, this.messages);
    }

//    public boolean compareTeamPlayers(List<Player> secondPlayers){
//        if (this.players.size() != secondPlayers.size()){
//            return false;
//        }
//
//        for (Player player : secondPlayers){
//            if (!this.players.containsKey(player.getUser().id())){
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public void addUserToTeam(Player player){
//        this.players.put(player.getUser().id(), player);
//    }
//
//    public boolean checkUserInTeam(Player player){
//        return this.players.containsValue(player);
//    }
//
//    public void removeUserFormTeam(Player player){
//        this.players.remove(player.getUser().id());
//    }
//
//    public boolean checkUserInMaster(Player player){
//        return this.master == player;
//    }
//
//    public void removeUserFromMaster(Player player){
//        if (player == master){
//            this.master = null;
//        }
//    }
//
//    public void selectMaster(Player player){
//        if (this.master == null){
//            this.master = player;
//        }
//    }
//
//    public boolean masterRoleAvailable(){
//        return this.master == null;
//    }
//
//    public boolean playerRoleAvailable(){
//        return true;
//    }
}
